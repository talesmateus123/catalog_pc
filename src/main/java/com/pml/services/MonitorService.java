/** 
 * This is the class "MonitorService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pml.domain.Equipment;
import com.pml.domain.Monitor;
import com.pml.dto.MonitorDTO;
import com.pml.dto.MonitorNewDTO;
import com.pml.repositories.MachineRepository;
import com.pml.repositories.MonitorRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class MonitorService {
	@Autowired
	private MonitorRepository repository;
	@Autowired
	private MachineRepository machineRepository;
	@Autowired
	private ComputerService computerService;
	
	public List<Monitor> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Monitor> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public Monitor findByPatrimonyId(String patrimonyId) {
		Optional<Monitor> object = this.repository.findByPatrimonyId(patrimonyId);
		return object.orElseThrow(()-> new ObjectNotFoundException("Monitor not found: patrimonyId: '" + patrimonyId + "'. Type: " + object.getClass().getName()));
	}
	
	public Monitor findById(Long id) {
		Optional<Monitor> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Monitor not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	@Transactional
	public Monitor insert(Monitor object) {
		if(alreadyExists(object.getPatrimonyId())){
			throw new ConflictOfObjectsException("This machine already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		object.setId(null);
		object.setCreatedDate(new Date());
		return this.repository.save(object);
	}

	public void delete(Long id) {
		this.findById(id);
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the monitor: id: '" + id + "'. This monitor has still dependents.");
		}
	}

	public Monitor update(Monitor object) {
		recoverData(object);	
		if(patrimonyIdIsChanged(object)){
			if(alreadyExists(object.getPatrimonyId()))
				throw new ConflictOfObjectsException("This machine already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}	
		return this.repository.saveAndFlush(object);		
	}
	
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object
	 * @return void
	 */
	private void recoverData(Monitor object) {
		Monitor oldMonitor = this.findById(object.getId());
		object.setCreatedDate(oldMonitor.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}
	
	/**
	 * Verify if already exists the patrimony id requested.
	 * @param Long patrimonyId
	 * @return boolean
	 */
	private boolean alreadyExists(String patrimonyId) {	
		Optional<Equipment> objectByPatrimonyId = this.machineRepository.findByPatrimonyId(patrimonyId);
		
		if(objectByPatrimonyId.isEmpty())
			return false; 
		return true;
	}
	
	
	/**
	 * Verify if the object in a question has its patrimony id changed.
	 * @param object
	 * @return boolean
	 */
	private boolean patrimonyIdIsChanged(Monitor object) {	
		Optional<Monitor> objectByPatrimonyId = this.repository.findByPatrimonyId(object.getPatrimonyId());		
		// Generates an exception if object doesn't exists 
		Monitor objectById = this.findById(object.getId());
		
		if(objectByPatrimonyId.isEmpty())
			return true;
		
		if(objectById.getPatrimonyId().equals(objectByPatrimonyId.get().getPatrimonyId()))
			return false;		
		return true;
	}	
	
	/**
	 * Convert the MonitorDTO object to a Monitor object. 
	 * @param monitorDTO MonitorDTO
	 * @return Monitor
	 */
	public Monitor fromDTO(MonitorDTO objectDTO) {
		
		//Long, String, Date, Date, String, String, String, Sector, boolean, Computer
		
		Monitor object = new Monitor(
				objectDTO.getId(), objectDTO.getPatrimonyId(), objectDTO.getCreatedDate(), 
				objectDTO.getLastModifiedDate(), objectDTO.getManufacturer(), objectDTO.getModel(),
				objectDTO.getDescription(),	objectDTO.getSector(), objectDTO.isItWorks(), null);
		return object;
	}
	
	/**
	 * Convert the MonitorNewDTO object to a Monitor object. 
	 * @param monitorNewDTO MonitorNewDTO
	 * @return Monitor
	 */
	public Monitor fromDTO(MonitorNewDTO objectNewDTO) {
		Monitor object = new Monitor(
				null, objectNewDTO.getPatrimonyId(), null, null, objectNewDTO.getManufacturer(), 
				objectNewDTO.getModel(), objectNewDTO.getDescription(), objectNewDTO.getSector(), 
				objectNewDTO.isItWorks(), null);
		if (objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));
		
		return object;
	}
	
	
}

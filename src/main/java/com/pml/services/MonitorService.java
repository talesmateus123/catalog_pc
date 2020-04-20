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

import com.pml.domain.Monitor;
import com.pml.dto.MonitorDTO;
import com.pml.dto.MonitorNewDTO;
import com.pml.repositories.ComputerRepository;
import com.pml.repositories.MonitorRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class MonitorService {
	@Autowired
	private MonitorRepository repository;	
	@Autowired
	private ComputerRepository computerRepository;
	
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
			throw new ConflictOfObjectsException("This monitor already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		object.setId(null);
		object.setCreatedDate(new Date());
		return this.repository.save(object);
	}

	public void delete(Long id) {
		Monitor objetc = this.findById(id);
		try {
			this.repository.deleteById(objetc.getId());
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the monitor: id: '" + id + "'. This monitor has still dependents.");
		}
	}

	public Monitor update(Monitor object) {
		if(patrimonyIdIsChanged(object)){
			if(alreadyExists(object.getPatrimonyId()))
				throw new ConflictOfObjectsException("This monitor already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		
		recoverData(object);		
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
		Optional<Monitor> objectByPatrimonyId = this.repository.findByPatrimonyId(patrimonyId);
		
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
		Optional<Monitor> objectById = this.repository.findById(object.getId());
		
		if(objectById.get().getPatrimonyId().equals(objectByPatrimonyId.get().getPatrimonyId()))
			return false;		
		return true;
	}	
	
	/**
	 * Convert the MonitorDTO object to a Monitor object. 
	 * @param monitorDTO MonitorDTO
	 * @return Monitor
	 */
	public Monitor fromDTO(MonitorDTO monitorDTO) {
		Monitor monitor = new Monitor(
				monitorDTO.getId(), monitorDTO.getPatrimonyId(), monitorDTO.getCreatedDate(), monitorDTO.getLastModifiedDate(),
				monitorDTO.getManufacturer(), monitorDTO.getModel(), monitorDTO.getDescription(), 
				monitorDTO.getSector().getCod(), monitorDTO.isItWorks(), null);
		return monitor;
	}
	
	/**
	 * Convert the MonitorNewDTO object to a Monitor object. 
	 * @param monitorNewDTO MonitorNewDTO
	 * @return Monitor
	 */
	public Monitor fromDTO(MonitorNewDTO monitorNewDTO) {
		Monitor monitor = new Monitor(
				null, monitorNewDTO.getPatrimonyId(), monitorNewDTO.getCreatedDate(), monitorNewDTO.getLastModifiedDate(),
				monitorNewDTO.getManufacturer(), monitorNewDTO.getModel(), monitorNewDTO.getDescription(), 
				monitorNewDTO.getSector().getCod(), monitorNewDTO.isItWorks(), null);
		monitor.setComputer(this.computerRepository.getOne(monitorNewDTO.getComputerId()));
		return monitor;
	}
	
	
}

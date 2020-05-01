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

import com.pml.domain.Computer;
import com.pml.domain.Monitor;
import com.pml.dto.MonitorNewDTO;
import com.pml.repositories.MonitorRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class MonitorService extends EquipmentService {
	@Autowired
	private MonitorRepository repository;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private ComputerService computerService;
	
	// List search methods
	public List<Monitor> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Monitor> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	@Override
	public Monitor findByPatrimonyId(String patrimonyId) {
		Optional<Monitor> object = this.repository.findByPatrimonyId(patrimonyId);
		return object.orElseThrow(()-> new ObjectNotFoundException("Monitor not found: patrimonyId: '" + patrimonyId + "'. Type: " + object.getClass().getName()));
	}
	
	@Override
	public Monitor findById(Long id) {
		Optional<Monitor> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Monitor not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Monitor findByComputer(Computer computer) {
		Optional<Monitor> object = this.repository.findByComputer(computer);
		return object.orElseThrow(()-> new ObjectNotFoundException("This computer: patrimonyId: '" + computer.getPatrimonyId() + "'has no monitor. Type: " + object.getClass().getName()));
	}

	// Create, update and delete methods
	@Transactional
	public Monitor insert(Monitor object) {
		if(this.alreadyExists(object.getPatrimonyId())){
			throw new ConflictOfObjectsException("This equipment already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
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
		this.recoverData(object);	
		if(this.patrimonyIdIsChanged(object)){
			if(this.alreadyExists(object.getPatrimonyId()))
				throw new ConflictOfObjectsException("This equipment already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}	
		return this.repository.saveAndFlush(object);		
	}
	
	// Auxiliary methods	
	/**
	 * Convert the MonitorNewDTO object to a Monitor object. 
	 * @param objectNewDTO MonitorNewDTO
	 * @return Monitor
	 */
	public Monitor fromDTO(MonitorNewDTO objectNewDTO) {
		Monitor object = new Monitor(
				null, objectNewDTO.getPatrimonyId(), null, null, objectNewDTO.getManufacturer(), 
				objectNewDTO.getModel(), objectNewDTO.getDescription(), null, 
				objectNewDTO.isItWorks(), null);
		if(objectNewDTO.getSectorId() != null)
			object.setSector(this.sectorService.findById(objectNewDTO.getSectorId()));
		if (objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));		
		return object;
	}
	
	
}

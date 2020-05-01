/** 
 * This is the class "ProcessorService". Which will be able to intermediate the repository class and the service class.
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
import com.pml.domain.Processor;
import com.pml.dto.ProcessorNewDTO;
import com.pml.repositories.ProcessorRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ProcessorService {
	@Autowired
	private ProcessorRepository repository;
	@Autowired
	private ComputerService computerService;

	// List search methods
	public List<Processor> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Processor> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	public Processor findById(Long id) {
		Optional<Processor> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Processor not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Processor findByComputer(Computer computer) {
		Optional<Processor> object = this.repository.findByComputer(computer);
		return object.orElseThrow(()-> new ObjectNotFoundException("This computer: patrimonyId: '" + computer.getPatrimonyId() + "'has no processor. Type: " + object.getClass().getName()));
	}

	// Create, update and delete methods
	@Transactional
	public Processor insert(Processor object) {
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
			throw new DataIntegrityException("Could not delete the processor: id: '" + id + "'. This processor has still dependents.");
		}
	}

	public Processor update(Processor object) {
		recoverData(object);
		return this.repository.saveAndFlush(object);		
	}

	// Auxiliary methods
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object
	 * @return void
	 */
	private void recoverData(Processor object) {
		Processor oldObject = this.findById(object.getId());
		object.setCreatedDate(oldObject.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}
	
	/**
	 * Convert the StorageDeviceNewDTO object to a StorageDevice object. 
	 * @param storageDeviceNewDTO StorageDeviceNewDTO
	 * @return StorageDevice
	 */
	public Processor fromDTO(ProcessorNewDTO objectNewDTO) {
		Processor object = new Processor(
				null, null, null, objectNewDTO.getManufacturer(), objectNewDTO.getModel(),
				objectNewDTO.getDescription(), objectNewDTO.isItWorks(), objectNewDTO.getProcessorName(), 
				objectNewDTO.getArchitecture(), null);
		if (objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));
		
		return object;
	}
	
	
}

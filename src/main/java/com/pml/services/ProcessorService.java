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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.domain.Processor;
import com.pml.dto.ProcessorNewDTO;
import com.pml.repositories.ProcessorRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

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
	
	public List<Processor> findAllWithoutComputer() {
		return this.repository.findAllByComputerNull();
	}
	
	public Page<Processor> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	public Processor findById(Long id) {
		return this.repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Processor not found: id: '" + id + "'. Type: " + Processor.class.getSimpleName()));
	}
	
	public Processor findByComputer(Computer computer) {
		return this.repository.findByComputer(computer).orElseThrow(()-> new ObjectNotFoundException("This computer: patrimonyId: '" + computer.getPatrimonyId() + "'has no processor. Type: " + Processor.class.getSimpleName()));
	}
	
	public Page<Processor> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {
		try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, Processor.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + Processor.class.getSimpleName() + "' class.");
        	return repository.search(searchTerm.toLowerCase(), pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }

	// Create, update and delete methods
	protected Processor save(Processor object) {
		if(object.getId() == null)
			return this.insert(object);
		return this.update(object);
	}
	
	@Transactional
	public Processor insert(Processor object) {
		object.setId(null);
		object.setCreatedDate(new Date());
		object.setLastModifiedDate(new Date());
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
		this.recoverData(object);
		return this.repository.saveAndFlush(object);		
	}

	// Auxiliary methods
	public boolean processorExists(Long id) {
		Optional<Processor> object = this.repository.findById(id);		
		if(object.get() != null)
			return true;
		return false;
	}
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object Processor
	 * @return void
	 */
	private void recoverData(Processor object) {
		Processor oldObject = this.findById(object.getId());
		object.setCreatedDate(oldObject.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}
	
	/**
	 * Convert the StorageDeviceNewDTO object to a StorageDevice object. 
	 * @param objectNewDTO StorageDeviceNewDTO
	 * @return StorageDevice
	 */
	public Processor fromDTO(ProcessorNewDTO objectNewDTO) {
		Processor object = new Processor(
				null, null, null, null, objectNewDTO.getManufacturer(), objectNewDTO.getModel(),
				objectNewDTO.getDescription(), objectNewDTO.getProcessorName(), 
				objectNewDTO.getArchitecture(), null);
		if(objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));
		
		if(objectNewDTO.getProcessorName() == null) {
			object.setProcessorName(null);
		}
		
		return object;
	}
	
	
	
}

/** 
 * This is the class "RamMemoryService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.domain.RamMemory;
import com.pml.dto.RamMemoryNewDTO;
import com.pml.repositories.RamMemoryRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

@Service
public class RamMemoryService {
	@Autowired
	private RamMemoryRepository repository;
	@Autowired
	private ComputerService computerService;

	// List search methods
	public List<RamMemory> findAll() {
		return this.repository.findAll();
	}
	
	public List<RamMemory> findAllWithoutComputer() {
		return this.repository.findAllByComputerNull();
	}
	
	public Page<RamMemory> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	public RamMemory findById(Long id) {
		return this.repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Ram memory not found: id: '" + id + "'. Type: " + RamMemory.class.getSimpleName()));
	}
	
	public List<RamMemory> findByComputer(Computer computer) {
		return this.repository.findByComputer(computer);
	}
	
	public Page<RamMemory> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {
		try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, RamMemory.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + RamMemory.class.getSimpleName() + "' class.");
        	return repository.search(searchTerm.toLowerCase(), pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }

	// Create, update and delete methods
	protected RamMemory save(RamMemory object) {
		if(object.getId() == null)
			return this.insert(object);
		return this.update(object);
	}
	
	@Transactional
	public RamMemory insert(RamMemory object) {
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
			throw new DataIntegrityException("Could not delete the ram memory: id: '" + id + "'. This user has still dependents.");
		}
	}

	public RamMemory update(RamMemory object) {
		this.recoverData(object);
		return this.repository.saveAndFlush(object);		
	}

	// Auxiliary methods
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object RamMemory
	 * @return void
	 */
	private void recoverData(RamMemory object) {
		RamMemory oldObject = this.findById(object.getId());
		object.setCreatedDate(oldObject.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}
	
	/**
	 * Convert the RamMemoryNewDTO object to a RamMemory object. 
	 * @param objectNewDTO RamMemoryNewDTO
	 * @return RamMemory
	 */
	public RamMemory fromDTO(RamMemoryNewDTO objectNewDTO) {
		RamMemory object = new RamMemory(
				null, null, null, null, objectNewDTO.getManufacturer(), objectNewDTO.getModel(),
				objectNewDTO.getDescription(), objectNewDTO.getSizeInGB(), 
				objectNewDTO.getArchitecture(), null);
		if (objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));
		
		return object;
	}
	
	
	
}

/** 
 * This is the class "RamMemoryService". Which will be able to intermediate the repository class and the service class.
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

import com.pml.domain.RamMemory;
import com.pml.dto.RamMemoryNewDTO;
import com.pml.repositories.RamMemoryRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class RamMemoryService {
	@Autowired
	private RamMemoryRepository repository;
	@Autowired
	private ComputerService computerService;
	
	public List<RamMemory> findAll() {
		return this.repository.findAll();
	}
	
	public Page<RamMemory> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public RamMemory findById(Long id) {
		Optional<RamMemory> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Ram memory not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	@Transactional
	public RamMemory insert(RamMemory object) {
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
			throw new DataIntegrityException("Could not delete the ram memory: id: '" + id + "'. This user has still dependents.");
		}
	}

	public RamMemory update(RamMemory object) {
		recoverData(object);
		return this.repository.saveAndFlush(object);		
	}
	
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object
	 * @return void
	 */
	private void recoverData(RamMemory object) {
		RamMemory oldObject = this.findById(object.getId());
		object.setCreatedDate(oldObject.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}
	
	/**
	 * Convert the RamMemoryNewDTO object to a RamMemory object. 
	 * @param objectDTO RamMemoryNewDTO
	 * @return RamMemory
	 */
	public RamMemory fromDTO(RamMemoryNewDTO objectNewDTO) {
		RamMemory object = new RamMemory(
				null, null, null, objectNewDTO.getManufacturer(), objectNewDTO.getModel(),
				objectNewDTO.getDescription(), objectNewDTO.isItWorks(), objectNewDTO.getSizeInMB(), 
				objectNewDTO.getArchitecture(), null);
		if (objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));
		
		return object;
	}
	
	
}

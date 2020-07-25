/** 
 * This is the class "StorageDeviceService". Which will be able to intermediate the repository class and the service class.
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
import com.pml.domain.StorageDevice;
import com.pml.dto.StorageDeviceNewDTO;
import com.pml.repositories.StorageDeviceRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

@Service
public class StorageDeviceService {
	@Autowired
	private StorageDeviceRepository repository;
	@Autowired
	private ComputerService computerService;

	// List search methods
	public List<StorageDevice> findAll() {
		return this.repository.findAll();
	}
	
	public List<StorageDevice> findAllWithoutComputer() {
		return this.repository.findAllByComputerNull();
	}

	// Simple search methods
	public Page<StorageDevice> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public StorageDevice findById(Long id) {
		Optional<StorageDevice> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Storage device not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public List<StorageDevice> findByComputer(Computer computer) {
		return this.repository.findByComputer(computer);
	}
	
	public Page<StorageDevice> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {
		try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, StorageDevice.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + StorageDevice.class.getName() + "' class.");
        	return repository.search(searchTerm.toLowerCase(), pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }

	// Create, update and delete methods
	public StorageDevice save(StorageDevice object) {
		if(object.getId() == null)
			return this.insert(object);
		return this.update(object);
	}
	
	@Transactional
	public StorageDevice insert(StorageDevice object) {
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
			throw new DataIntegrityException("Could not delete the storage device: id: '" + id + "'. This user has still dependents.");
		}
	}

	public StorageDevice update(StorageDevice object) {
		recoverData(object);
		return this.repository.saveAndFlush(object);		
	}

	// Auxiliary methods
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object StorageDevice
	 * @return void
	 */
	private void recoverData(StorageDevice object) {
		StorageDevice oldObject = this.findById(object.getId());
		object.setCreatedDate(oldObject.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}

	/**
	 * Convert the StorageDeviceNewDTO object to a StorageDevice object. 
	 * @param objectNewDTO StorageDeviceNewDTO
	 * @return StorageDevice
	 */
	public StorageDevice fromDTO(StorageDeviceNewDTO objectNewDTO) {
		StorageDevice object = new StorageDevice(
				null, null, null, objectNewDTO.getManufacturer(), objectNewDTO.getModel(),
				objectNewDTO.getDescription(), objectNewDTO.isItWorks(), objectNewDTO.getSizeInGB(), 
				objectNewDTO.getArchitecture(), objectNewDTO.getType(), null);
		if (objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));
		
		return object;
	}
	
	
	
}

/** 
 * This is the class "SectorService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Sector;
import com.pml.dto.SectorNewDTO;
import com.pml.repositories.SectorRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

@Service
public class SectorService {
	@Autowired
	private SectorRepository repository;
	
	// List search methods
	public List<Sector> findAll() {
		return this.repository.findByOrderByName();
	}
	
	public Page<Sector> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	public Sector findById(Integer id) {
		Optional<Sector> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Sector not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Page<Sector> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {
		try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, Sector.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + Sector.class.getName() + "' class.");
        	return repository.search(searchTerm.toLowerCase(), pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }

	// Create, update and delete methods
	@Transactional
	public Sector insert(Sector object) {
		if(this.alreadyExistsWithName(object))
			throw new ConflictOfObjectsException("This sector already exists: patrimonyId: '" + object.getName() + "'.");
		if(this.alreadyExistsWithPhoneNumber(object))
			throw new ConflictOfObjectsException("Another sector uses this same phone number: '" + object.getPhone() + "'.");
		object.setId(null);
		return this.repository.save(object);
	}

	public void delete(Integer id) {
		this.findById(id);				
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the object: id: '" + id + "'. This object has still dependents.");
		}
		
	}

	public Sector update(Sector object) {
		if(object.getPhone() != null) {
			if(this.isPhoneNumberChanged(object)) {
				if(this.alreadyExistsWithName(object))
					throw new ConflictOfObjectsException("This sector already exists: patrimonyId: '" + object.getName() + "'.");
				if(this.alreadyExistsWithPhoneNumber(object))
					throw new ConflictOfObjectsException("Another sector uses this same phone number: '" + object.getPhone() + "'.");
			}
		}
		return this.repository.saveAndFlush(object);
	}

	// Auxiliary methods	
	/**
	 * Checks if another sector already exists with the requested name.
	 * @param name String
	 * @return boolean
	 */
	private boolean alreadyExistsWithName(Sector object) {
		Optional<Sector> objectByName = this.repository.findByName(object.getName()); 
		
		if(!objectByName.isEmpty()) {
			if(object.getId() != null) {
				if(object.getId().equals(objectByName.get().getId()) && object.getName().equals(objectByName.get().getName()))
					return false;
			}
			else
				return true;
		}
		return false;
	}
	
	//agendar visita assistencia cabeamento
	
	/**
	 * Checks if another sector already exists with the requested phone number.
	 * @param name String
	 * @return boolean
	 */
	private boolean alreadyExistsWithPhoneNumber(Sector object) {
		if(object.getPhone() == null)
			return false;
		
		Optional<Sector> objectByPhone = this.repository.findByPhone(object.getPhone());  
		
		if(!objectByPhone.isEmpty()) {
			if(object.getId().equals(objectByPhone.get().getId()) && object.getPhone().equals(objectByPhone.get().getPhone()))
				return false;
			else
				return true;
		}
		return false;
	}
	
	/**
	 * Checks if the object in a question has its phone changed.
	 * @param object Sector
	 * @return boolean
	 */
	protected boolean isPhoneNumberChanged(Sector object) {
		Optional<Sector> objectByPhoneNumber = this.repository.findByPhone(object.getPhone());	
		// Generates an exception if object doesn't exists 
		Sector objectById = this.findById(object.getId());
		
		if(objectByPhoneNumber.isEmpty())
			return true;
		
		if(objectById.getPhone() != null) {
			if(objectById.getPhone().equals(objectByPhoneNumber.get().getPhone()))
				return false;		
		}
		return true;
	}
	
	/**
	 * Convert the SectorNewDTO object to a Sector object. 
	 * @param objectNewDTO SectorNewDTO
	 * @return Sector
	 */
	public Sector fromDTO(SectorNewDTO objectNewDTO) {
		Sector object = new Sector(null, objectNewDTO.getName(), objectNewDTO.getPhone());
		
		return object;
	}
	
	
	
}

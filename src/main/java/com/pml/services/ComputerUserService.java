/** 
 * This is the class "ComputerUserService". Which will be able to intermediate the repository class and the service class.
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

import com.pml.domain.Computer;
import com.pml.domain.ComputerUser;
import com.pml.domain.Sector;
import com.pml.dto.ComputerUserNewDTO;
import com.pml.repositories.ComputerUserRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

@Service
public class ComputerUserService {
	@Autowired
	private ComputerUserRepository repository;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private ComputerService computerService;

	// List search methods
	public List<ComputerUser> findAll() {
		return this.repository.findByOrderByName();
	}
	
	public Page<ComputerUser> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}
	
	public List<ComputerUser> findByName(String name) {
		return this.repository.findByName(name);
	}
	
	public List<ComputerUser> findByLastName(String lastName) {
		return this.repository.findByLastName(lastName);
	}
	
	public List<ComputerUser> findByComputer(Computer computer) {
		return this.repository.findByUseTheComputers(computer);
	}
	
	public List<ComputerUser> findBySector(Sector sector) {
		return this.repository.findBySector(sector);
	}

	// Simple search methods
	public ComputerUser findById(Long id) {
		Optional<ComputerUser> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer user not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public ComputerUser findByEmail(String email) {
		Optional<ComputerUser> object = this.repository.findByEmail(email);
		return object.orElseThrow(()-> new ObjectNotFoundException("This ipAddress: '" + email + "'has no computer user. Type: " + object.getClass().getName()));
	}
	
	public Page<ComputerUser> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {
		try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, ComputerUser.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + ComputerUser.class.getName() + "' class.");
        	return repository.search(searchTerm.toLowerCase(), pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }
	
	// Create, update and delete methods
	@Transactional
	public ComputerUser insert(ComputerUser object) {
		object.setId(null);
		return this.repository.save(object);
	}

	public void delete(Long id) {
		this.findById(id);
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the computer user: id: '" + id + "'. This user has still dependents.");
		}
	}

	@Transactional
	public ComputerUser update(ComputerUser object) {
		this.findById(object.getId());
		return this.repository.saveAndFlush(object);		
	}

	// Auxiliary methods
	/**
	 * Convert the ComputerUserNewDTO object to a ComputerUser object. 
	 * @param objectNewDTO ComputerUserNewDTO
	 * @return ComputerUser
	 */
	public ComputerUser fromDTO(ComputerUserNewDTO objectNewDTO) {
		ComputerUser object = new ComputerUser(
				null, objectNewDTO.getName(), objectNewDTO.getLastName(),
				null, objectNewDTO.getEmail());		

		if(objectNewDTO.getSectorId() != null)
			object.setSector(this.sectorService.findById(objectNewDTO.getSectorId()));
		
		if(objectNewDTO.getUseTheComputersId() != null) {
			for(Long computerId : objectNewDTO.getUseTheComputersId()) {
				Computer computer = this.computerService.findById(computerId);
				computer.addComputerUser(object);
				object.addUseTheComputer(computer);
			}
		}
		return object;
	}
	
	
	
}

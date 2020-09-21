/** 
 * This is the class "ComputerService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.repositories.ComputerRepository;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

@Service
public class SupportService {
	@Autowired
	private ComputerRepository repository;

	// List search methods
	public List<Computer> findAll() {
		return this.repository.findByOrderByPatrimonyIdAndTeamViewerIdNotNull();
	}
	
	public Page<Computer> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {		
		try {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, Computer.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + Computer.class.getName() + "' class.");
        	
        	return this.repository.findPageByOrderByPatrimonyIdAndTeamViewerIdNotNull(pageRequest);
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
	}
	
	// Simple search methods
	public Computer findById(Long id) {
		return this.repository.findByIdAndTeamViewerIdNotNull(id).orElseThrow(()-> new ObjectNotFoundException("Computer not found: id: '" + id + "'. Type: " + Computer.class.getName()));
	}
	
	// Generalized search
	public Page<Computer> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {		
    	try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, Computer.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + Computer.class.getName() + "' class.");
        	
        	return repository.searchByTeamViewerIdNotNull(searchTerm.toLowerCase(), pageRequest);
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }	
	
	
	
}

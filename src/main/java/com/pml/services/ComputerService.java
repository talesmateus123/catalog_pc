/** 
 * This is the class "ComputerService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.repositories.ComputerRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ComputerService {
	@Autowired
	private ComputerRepository repository;
	
	public List<Computer> findAll() {
		return this.repository.findAll();
	}
	
	public Computer find(String id) {
		Optional<Computer> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer not found: " + id + ". Type: " + object.getClass().getName()));
	}
	
	public Computer insert(Computer object) {
		object.setCreatedDate(new Date());
		return this.repository.save(object);
	}

	public void delete(String id) {
		this.find(id);
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the computer: " + id + ". This computer has still dependents.");
		}
	}

	public Computer update(Computer object) {
		this.find(object.getPatrimonyId());
		
		object.setCreatedDate(this.repository.getOne(object.getPatrimonyId()).getCreatedDate());
		object.setModifiedDate(new Date());
		return this.repository.saveAndFlush(object);
		
	}
}

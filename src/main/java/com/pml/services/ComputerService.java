/** 
 * This is the class "ComputerService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pml.models.Computer;
import com.pml.repositories.ComputerRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ComputerService {
	@Autowired
	private ComputerRepository repository;
	
	public List<Computer> list() {
		return this.repository.findAll();
	}
	
	public Computer search(String id) {
		Optional<Computer> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Object not found: " + id + ", type: " + object.getClass().getName()));
	}
	
	public Computer add(Computer object) {
		Computer savedObject = this.repository.save(object); 
		if(object.equals(savedObject))
			return savedObject;
		else 
			return null;
	}

	public boolean delete(String id) {
		Optional<Computer> existingObject = this.repository.findById(id);
		if(existingObject.isEmpty())
			return false;
		this.repository.deleteById(id);
		return true;
	}

	public Computer update(Computer object) {
		Optional<Computer> existingObject = this.repository.findById(object.getPatrimonyId());
		if(existingObject.isEmpty())
			return null;
		return this.repository.saveAndFlush(object);
		
	}
}

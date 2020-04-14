/** 
 * This is the class "ComputerUserService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pml.models.ComputerUser;
import com.pml.repositories.ComputerUserRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ComputerUserService {
	@Autowired
	private ComputerUserRepository repository;
	
	public List<ComputerUser> list() {
		return this.repository.findAll();
	}
	
	public ComputerUser search(Long id) {		
		Optional<ComputerUser> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Object not found: "+id));
	}
	
	public ComputerUser add(ComputerUser object) {
		ComputerUser savedObject = this.repository.save(object); 
		if(object.equals(savedObject))
			return savedObject;
		else 
			return null;
	}

	public boolean delete(Long id) {
		Optional<ComputerUser> existingObject = this.repository.findById(id);
		if(existingObject.isEmpty())
			return false;
		this.repository.deleteById(id);
		return true;
	}

	public ComputerUser update(ComputerUser object) {
		Optional<ComputerUser> existingObject = this.repository.findById(object.getId());
		if(existingObject.isEmpty())
			return null;
		return this.repository.saveAndFlush(object);
		
	}
}

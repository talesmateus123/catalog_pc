/** 
 * This is the class "MonitorService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pml.domain.Monitor;
import com.pml.repositories.MonitorRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class MonitorService {
	@Autowired
	private MonitorRepository repository;
	
	public List<Monitor> list() {
		return this.repository.findAll();
	}
	
	public Monitor find(String id) {
		Optional<Monitor> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Object not found: " + id + ", type: " + object.getClass().getName()));
	}
	
	public Monitor insert(Monitor object) {
		object.setCreatedDate(new Date());
		return this.repository.save(object);
	}

	public boolean delete(String id) {		
		Optional<Monitor> existingObject = this.repository.findById(id);
		if(existingObject.isEmpty())
			return false;
		this.repository.deleteById(id);
		return true;
	}

	public Monitor update(Monitor object) {
		object.setCreatedDate(this.repository.getOne(object.getPatrimonyId()).getCreatedDate());
		object.setModifiedDate(new Date());
		Optional<Monitor> existingObject = this.repository.findById(object.getPatrimonyId());
		if(existingObject.isEmpty())
			return null;
		return this.repository.saveAndFlush(object);
		
	}
}

package com.pml.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pml.models.Monitor;
import com.pml.repositories.MonitorRepository;

@Service
public class ComputerService {
	@Autowired
	private MonitorRepository repository;
	
	public List<Monitor> list() {
		return this.repository.findAll();
	}
	
	public Monitor search(String id) {
		Optional<Monitor> object = this.repository.findById(id);
		if(object.isEmpty())
			return null;
		return object.get();
	}
	
	public Monitor add(Monitor object) {
		Monitor savedObject = this.repository.save(object); 
		if(object.equals(savedObject))
			return savedObject;
		else 
			return null;
	}

	public boolean delete(String id) {
		Optional<Monitor> existingObject = this.repository.findById(id);
		if(existingObject.isEmpty())
			return false;
		this.repository.deleteById(id);
		return true;
	}

	public Monitor update(Monitor object) {
		Optional<Monitor> existingObject = this.repository.findById(object.getPatrimonyId());
		if(existingObject.isEmpty())
			return null;
		return this.repository.saveAndFlush(object);
		
	}
}

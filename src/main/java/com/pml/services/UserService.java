package com.pml.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pml.models.User;
import com.pml.repositories.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> list() {
		return this.repository.findAll();
	}
	
	public User search(Long id) throws ObjectNotFoundException {
		Optional<User> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Object not found: "+id, "User"));
	}
	
	public User add(User object) {
		User savedObject = this.repository.save(object); 
		if(object.equals(savedObject))
			return savedObject;
		else 
			return null;
	}

	public boolean delete(Long id) {
		Optional<User> existingObject = this.repository.findById(id);
		if(existingObject.isEmpty())
			return false;
		this.repository.deleteById(id);
		return true;
	}

	public User update(User object) {
		Optional<User> existingObject = this.repository.findById(object.getId());
		if(existingObject.isEmpty())
			return null;
		return this.repository.saveAndFlush(object);
		
	}
}

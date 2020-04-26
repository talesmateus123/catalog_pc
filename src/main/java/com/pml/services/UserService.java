/** 
 * This is the class "UserService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pml.domain.User;
import com.pml.dto.UserDTO;
import com.pml.dto.UserNewDTO;
import com.pml.repositories.UserRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return this.repository.findAll();
	}
	
	public Page<User> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public User findById(Long id) {
		Optional<User> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("User not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public User insert(User object) {
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

	public User update(User object) {
		this.findById(object.getId());
		return this.repository.saveAndFlush(object);		
	}
	
	/*
	/**
	 * Convert the UserDTO object to a User object. 
	 * @param UserDTO UserDTO
	 * @return User
	 */
	public User fromDTO(UserDTO UserDTO) {
		User User = new User(
				UserDTO.getId(), UserDTO.getLogin(), UserDTO.getPassword());
		return User;
	}
	
	/**
	 * Convert the UserNewDTO object to a User object. 
	 * @param UserNewDTO UserNewDTO
	 * @return User
	 */
	public User fromDTO(UserNewDTO UserNewDTO) {
		User User = new User(null, UserNewDTO.getLogin(), this.bCryptPasswordEncoder.encode(UserNewDTO.getPassword()));
		
		return User;
	}
	
	
}

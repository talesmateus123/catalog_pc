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

import com.pml.domain.Client;
import com.pml.domain.enums.UserProfile;
import com.pml.dto.ClientDTO;
import com.pml.dto.ClientNewDTO;
import com.pml.repositories.ClientRepository;
import com.pml.security.UserSS;
import com.pml.services.exceptions.AuthorizationException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ClientRepository repository;
	
	public List<Client> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public Client findById(Long id) {
		UserSS user = UserService.authenticated();
		if (user == null || user.hasHole(UserProfile.ADMIN) && !id.equals(user.getId()))
			throw new AuthorizationException("Access denied");
		Optional<Client> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("User not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Client findByEmail(String email) {
		Optional<Client> object = this.repository.findByEmail(email);
		return object.orElseThrow(()-> new ObjectNotFoundException("User not found: id: '" + email + "'. Type: " + object.getClass().getName()));
	}
	
	public Client insert(Client object) {
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

	public Client update(Client object) {
		this.findById(object.getId());
		return this.repository.saveAndFlush(object);		
	}
	
	/*
	/**
	 * Convert the ClientDTO object to a User object. 
	 * @param ClientDTO ClientDTO
	 * @return User
	 */
	public Client fromDTO(ClientDTO ClientDTO) {
		Client User = new Client(
				ClientDTO.getId(), ClientDTO.getEmail(), ClientDTO.getPassword());
		return User;
	}
	
	/**
	 * Convert the ClientNewDTO object to a User object. 
	 * @param ClientNewDTO ClientNewDTO
	 * @return User
	 */
	public Client fromDTO(ClientNewDTO ClientNewDTO) {
		Client User = new Client(null, ClientNewDTO.getEmail(), this.bCryptPasswordEncoder.encode(ClientNewDTO.getPassword()));
		
		return User;
	}
	
	
}

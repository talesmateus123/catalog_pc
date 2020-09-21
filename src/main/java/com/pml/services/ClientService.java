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

	// List search methods
	public List<Client> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	public Client findById(Long id) {
		UserSS user = UserService.authenticated();
		if (user == null || !id.equals(user.getId()))
			throw new AuthorizationException("Access denied");
		return this.repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("User not found: id: '" + id + "'. Type: " + Client.class.getSimpleName()));
	}
	
	public Client findByEmail(String email) {
		Optional<Client> optionalClient = this.repository.findByEmail(email);
		UserSS user = UserService.authenticated();
		optionalClient.ifPresent(client -> {
			if (user == null || !client.getId().equals(user.getId()))
				throw new AuthorizationException("Access denied");
		});
		return optionalClient.orElseThrow(()-> new ObjectNotFoundException("User not found: e-mail: '" + email + "'. Type: " + Client.class.getSimpleName()));
	}


	// Create, update and delete methods
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
		Client client = this.findById(object.getId());
		if(client.getProfiles().contains(UserProfile.ADMIN))
			client.getProfiles().forEach(profile -> object.addProfile(profile));
		return this.repository.saveAndFlush(object);
	}

	// Auxiliary methods
	/**
	 * Convert the ClientDTO object to a User object. 
	 * @param objectDTO ClientDTO
	 * @return User
	 */
	public Client fromDTO(ClientDTO objectDTO) {
		return new Client(objectDTO.getId(), objectDTO.getEmail(), objectDTO.getName(), objectDTO.getPassword());
	}
	
	/**
	 * Convert the ClientNewDTO object to a User object. 
	 * @param objectNewDTO ClientNewDTO
	 * @return User
	 */
	public Client fromDTO(ClientNewDTO objectNewDTO) {
		return new Client(null, objectNewDTO.getEmail(), objectNewDTO.getName(), this.bCryptPasswordEncoder.encode(objectNewDTO.getPassword()));
	}
	
	
	
}

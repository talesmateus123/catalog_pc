/** 
 * This is the class "AddressService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pml.domain.Address;
import com.pml.repositories.AddressRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;

	// List search methods
	public List<Address> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Address> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	public Address findByName(String name) {
		Optional<Address> object = this.repository.findByName(name);
		return object.orElseThrow(()-> new ObjectNotFoundException("Address not found: name: '" + name + "'. Type: " + object.getClass().getName()));
	}
	
	public Address findById(Integer id) {
		Optional<Address> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Address not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}

	// Create, update and delete methods
	public Address insert(Address object) {
		object.setId(null);
		return this.repository.save(object);
	}
	
	public Address update(Address object) {
		return this.repository.saveAndFlush(object);		
	}
	
	
	
}

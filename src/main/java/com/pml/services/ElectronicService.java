/** 
 * This is the class "EquipmentService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pml.domain.Electronic;
import com.pml.repositories.ElectronicRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ElectronicService {
	@Autowired
	private ElectronicRepository repository;
		
	public Electronic findById(Long id) {
		return this.repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Electronic not found: id: '" + id + "'. Type: " + Electronic.class.getSimpleName()));
	}
	
	
	
}

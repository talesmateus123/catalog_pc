/** 
 * This is the class "ComputerResource". Which will be to represent a REST controller of Computer model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pml.domain.Equipment;
import com.pml.repositories.EquipmentRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/api/equipments")
public class EquipmentResource {
	@Autowired
	private EquipmentRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Equipment> findById(@PathVariable Long id) {
		Optional<Equipment> object = this.repository.findById(id);
		
		if(object.isPresent())
			throw new ObjectNotFoundException("Equipment not found: id: '" + id + "'. Type: " + object.getClass().getName());
		else		
			return ResponseEntity.ok().body(object.get());
	}	
	
	
	
}

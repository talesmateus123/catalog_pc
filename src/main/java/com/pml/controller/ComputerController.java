/** 
 * This is the class "ComputerController". Which will be to represent a REST controller of Computer model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pml.model.Computer;
import com.pml.repository.ComputerRepository;

@RestController
@RequestMapping(value = "/api/computers")
public class ComputerController {
	@Autowired
	private ComputerRepository repository;
	
	@GetMapping
	public List<Computer> list() {
		return this.repository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<Computer> search(@PathVariable String id) {
		Optional<Computer> object = this.repository.findByPatrimonyId(id);
		if(object.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(object.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Computer add(@Valid @RequestBody Computer object) {
		Computer savedObject = this.repository.save(object); 
		if(object.equals(savedObject))
			return savedObject;
		else 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Já existe uma oportunidade para este prospecto com a mesma descrição");
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Boolean> delete(@PathVariable String id) {
		Optional<Computer> existingObject = this.repository.findByPatrimonyId(id);
		if(existingObject.isEmpty())
			return ResponseEntity.notFound().build();
		
		this.repository.delete(existingObject.get());
		return ResponseEntity.ok(true);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Computer> update(@Valid @RequestBody Computer object) {
		Optional<Computer> existingObject = this.repository.findByPatrimonyId(object.getPatrimonyId());
		if(existingObject.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(this.repository.saveAndFlush(object));
		
	}
	
}

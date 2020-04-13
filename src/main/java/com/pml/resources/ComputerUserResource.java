/** 
 * This is the class "ComputerUserResource". Which will be to represent a REST controller of ComputerUser model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

import com.pml.models.ComputerUser;
import com.pml.repositories.ComputerUserRepository;

@RestController
@RequestMapping(value = "/api/computer_users")
public class ComputerUserResource {
	@Autowired
	private ComputerUserRepository repository;
	
	@GetMapping
	public List<ComputerUser> list() {
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ComputerUser> search(@PathVariable Long id) {
		Optional<ComputerUser> object = this.repository.findById(id);
		if(object.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(object.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComputerUser add(@Valid @RequestBody ComputerUser object) {
		ComputerUser savedObject = this.repository.save(object); 
		if(object.equals(savedObject))
			return savedObject;
		else 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Já existe uma oportunidade para este prospecto com a mesma descrição");
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Optional<ComputerUser> existingObject = this.repository.findById(id);
		if(existingObject.isEmpty())
			return ResponseEntity.notFound().build();
		
		this.repository.deleteById(id);
		return ResponseEntity.ok(true);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<ComputerUser> update(@Valid @RequestBody ComputerUser object) {
		Optional<ComputerUser> existingObject = this.repository.findById(object.getId());
		if(existingObject.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(this.repository.saveAndFlush(object));
		
	}
	
}

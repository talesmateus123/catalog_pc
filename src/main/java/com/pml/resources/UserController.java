/** 
 * This is the class "UserController". Which will be to represent a REST controller of User model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pml.models.User;
import com.pml.services.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping
	public List<User> list() {
		return this.service.list();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> search(@PathVariable Long id) {
		User object = this.service.search(id);
		return ResponseEntity.ok(object);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> add(@Valid @RequestBody User object) {
		User savedObject = this.service.add(object); 
		return ResponseEntity.ok(savedObject);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.ok(true);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<User> update(@Valid @RequestBody User object) {
		return ResponseEntity.ok(this.service.update(object));
	}
	
}

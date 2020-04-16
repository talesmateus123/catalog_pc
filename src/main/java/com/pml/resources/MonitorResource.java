/** 
 * This is the class "MonitorResource". Which will be to represent a REST controller of Monitor model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pml.domain.Monitor;
import com.pml.services.MonitorService;

@RestController
@RequestMapping(value = "/api/monitors")
public class MonitorResource {
	@Autowired
	private MonitorService service;
	
	@GetMapping
	public List<Monitor> list() {
		return this.service.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Monitor> search(@PathVariable String id) {
		Monitor object = this.service.search(id);
		if(object == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(object);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Monitor add(@Valid @RequestBody Monitor object) {
		Monitor savedObject = this.service.add(object); 
		if(object.equals(savedObject))
			return savedObject;
		else 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Request error");
	}

}

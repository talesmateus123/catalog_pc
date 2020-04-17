/** 
 * This is the class "MonitorResource". Which will be to represent a REST controller of Monitor model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pml.domain.Monitor;
import com.pml.services.MonitorService;


@RestController
@RequestMapping(value = "/api/monitors")
public class MonitorResource {
	@Autowired
	private MonitorService service;
	
	@GetMapping
	public List<Monitor> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Monitor> find(@PathVariable String id) {
		Monitor object = this.service.find(id);
		if(object == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(object);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Monitor object) {
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(object.getPatrimonyId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}

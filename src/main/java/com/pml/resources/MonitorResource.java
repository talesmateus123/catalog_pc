/** 
 * This is the class "MonitorResource". Which will be to represent a REST controller of Monitor model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pml.domain.Monitor;
import com.pml.dto.MonitorDTO;
import com.pml.dto.MonitorNewDTO;
import com.pml.services.MonitorService;

@RestController
@RequestMapping(value = "/api/monitors")
public class MonitorResource {
	@Autowired
	
	private MonitorService service;
	
	@GetMapping
	public ResponseEntity<List<MonitorDTO>> findAll() {
		List<Monitor> objects = this.service.findAll();
		List<MonitorDTO> objectsDTO = objects.stream().map(
				obj -> new MonitorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<MonitorDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "motherBoardName") String orderBy) {
		
		Page<Monitor> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<MonitorDTO> objectsDTO = objects.map(obj -> new MonitorDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Monitor> findById(@PathVariable Long id) {
		Monitor object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping("/patrimonyId/{patrimonyId}")
	public ResponseEntity<Monitor> findByPatrimonyId(@PathVariable String patrimonyId) {
		Monitor object = this.service.findByPatrimonyId(patrimonyId);
		return ResponseEntity.ok().body(object);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody MonitorNewDTO objectDTO) {
		Monitor object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody MonitorDTO objectDTO, @PathVariable Long id) {
		Monitor object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	
}

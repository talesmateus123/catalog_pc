/** 
 * This is the class "ComputerResource". Which will be to represent a REST controller of Computer model.
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

import com.pml.domain.Computer;
import com.pml.dto.ComputerDTO;
import com.pml.dto.ComputerNewDTO;
import com.pml.services.ComputerService;

@RestController
@RequestMapping(value = "/api/computers")
public class ComputerResource {
	@Autowired
	
	private ComputerService service;
	
	@GetMapping
	public ResponseEntity<List<ComputerDTO>> findAll() {
		List<Computer> objects = this.service.findAll();
		List<ComputerDTO> objectsDTO = objects.stream().map(
				obj -> new ComputerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ComputerDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "motherBoardName") String orderBy) {
		
		Page<Computer> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Computer> findById(@PathVariable Long id) {
		Computer object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping("/patrimonyId/{patrimonyId}")
	public ResponseEntity<Computer> findByPatrimonyId(@PathVariable String patrimonyId) {
		Computer object = this.service.findByPatrimonyId(patrimonyId);
		return ResponseEntity.ok().body(object);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ComputerNewDTO objectDTO) {
		Computer object = this.service.fromDTO(objectDTO);
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
	public ResponseEntity<Void> update(@Valid @RequestBody ComputerNewDTO objectDTO, @PathVariable Long id) {
		Computer object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	
}

/** 
 * This is the class "SectorResource". Which will be to represent a REST controller of Sector model.
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
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.pml.domain.Sector;
import com.pml.dto.SectorDTO;
import com.pml.dto.SectorNewDTO;
import com.pml.services.SectorService;

@RestController
@RequestMapping(value = "/api/sectors")
public class SectorResource {
	@Autowired
	
	private SectorService service;
	
	@GetMapping
	public ResponseEntity<List<SectorDTO>> findAll() {
		List<Sector> objects = this.service.findAll();
		List<SectorDTO> objectsDTO = objects.stream().map(
				obj -> new SectorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<SectorDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "motherBoardName") String orderBy) {
		
		Page<Sector> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<SectorDTO> objectsDTO = objects.map(obj -> new SectorDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sector> findById(@PathVariable Long id) {
		Sector object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping("/name")
	public ResponseEntity<Sector> findByName(@RequestParam(value = "value") String name) {
		Sector object = this.service.findByName(name);
		return ResponseEntity.ok().body(object);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody SectorNewDTO objectDTO) {
		Sector object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody SectorNewDTO objectDTO, @PathVariable Long id) {
		Sector object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	
	
}

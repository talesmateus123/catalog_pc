/** 
 * This is the class "RamMemoryResource". Which will be to represent a REST controller of RamMemory model.
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
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.pml.domain.RamMemory;
import com.pml.dto.RamMemoryDTO;
import com.pml.dto.RamMemoryNewDTO;
import com.pml.services.RamMemoryService;

@RestController
@RequestMapping(value = "/api/ram_memories")
public class RamMemoryResource {
	@Autowired	
	private RamMemoryService service;
	
	@GetMapping
	public ResponseEntity<List<RamMemoryDTO>> findAll() {
		List<RamMemory> objects = this.service.findAll();
		List<RamMemoryDTO> objectsDTO = objects.stream().map(
				obj -> new RamMemoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<RamMemoryDTO>> findAllWithoutComputer() {
		List<RamMemory> objects = this.service.findAllWithoutComputer();
		List<RamMemoryDTO> objectsDTO = objects.stream().map(
				obj -> new RamMemoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<RamMemoryDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "motherBoardName") String orderBy) {
		
		Page<RamMemory> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<RamMemoryDTO> objectsDTO = objects.map(obj -> new RamMemoryDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RamMemory> findById(@PathVariable Long id) {
		RamMemory object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8100")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody RamMemoryNewDTO objectDTO) {
		RamMemory object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8100")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:8100")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody RamMemoryNewDTO objectDTO, @PathVariable Long id) {
		RamMemory object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	
	
}

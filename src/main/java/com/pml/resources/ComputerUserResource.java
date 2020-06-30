/** 
 * This is the class "ComputerUserResource". Which will be to represent a REST controller of ComputerUser model.
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

import com.pml.domain.ComputerUser;
import com.pml.dto.ComputerUserDTO;
import com.pml.dto.ComputerUserNewDTO;
import com.pml.services.ComputerUserService;

@RestController
@RequestMapping(value = "/api/computer_users")
public class ComputerUserResource {
	@Autowired	
	private ComputerUserService service;
	
	@GetMapping
	public ResponseEntity<List<ComputerUserDTO>> findAll() {
		List<ComputerUser> objects = this.service.findAll();
		List<ComputerUserDTO> objectsDTO = objects.stream().map(
				obj -> new ComputerUserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ComputerUserDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		
		Page<ComputerUser> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<ComputerUserDTO> objectsDTO = objects.map(obj -> new ComputerUserDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ComputerUser> findById(@PathVariable Long id) {
		ComputerUser object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping("/search")
    public Page<ComputerUser> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam("direction") String direction, 
            @RequestParam("orderBy") String orderBy,
    		@RequestParam("searchTerm") String searchTerm) {
        return service.search(page, linesPerPage, direction, orderBy, searchTerm);
    }
	
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ComputerUserNewDTO objectDTO) {
		ComputerUser object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ComputerUserNewDTO objectDTO, @PathVariable Long id) {
		ComputerUser object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	
	
}

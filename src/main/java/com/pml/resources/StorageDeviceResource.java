/** 
 * This is the class "StorageDeviceResource". Which will be to represent a REST controller of StorageDevice model.
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

import com.pml.domain.StorageDevice;
import com.pml.dto.StorageDeviceDTO;
import com.pml.dto.StorageDeviceNewDTO;
import com.pml.services.StorageDeviceService;

@RestController
@RequestMapping(value = "/api/storage_devices")
public class StorageDeviceResource {
	@Autowired	
	private StorageDeviceService service;
	
	@GetMapping
	public ResponseEntity<List<StorageDeviceDTO>> findAll() {
		List<StorageDevice> objects = this.service.findAll();
		List<StorageDeviceDTO> objectsDTO = objects.stream().map(
				obj -> new StorageDeviceDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<StorageDeviceDTO>> findAllWithoutComputer() {
		List<StorageDevice> objects = this.service.findAllWithoutComputer();
		List<StorageDeviceDTO> objectsDTO = objects.stream().map(
				obj -> new StorageDeviceDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<StorageDeviceDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "motherBoardName") String orderBy) {
		
		Page<StorageDevice> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<StorageDeviceDTO> objectsDTO = objects.map(obj -> new StorageDeviceDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StorageDevice> findById(@PathVariable Long id) {
		StorageDevice object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody StorageDeviceNewDTO objectDTO) {
		StorageDevice object = this.service.fromDTO(objectDTO);
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
	public ResponseEntity<Void> update(@Valid @RequestBody StorageDeviceNewDTO objectDTO, @PathVariable Long id) {
		StorageDevice object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	
	
}

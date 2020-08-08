/** 
 * This is the class "ComputerUserResource". Which will be to represent a REST controller of ComputerUser model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pml.domain.ComputerUser;
import com.pml.dto.ComputerUserDTO;
import com.pml.dto.ComputerUserNewDTO;
import com.pml.services.ComputerUserService;
import com.pml.util.GeneratePdfReportFromComputerUser;

@RestController
@RequestMapping(value = "/api/computer_users")
public class ComputerUserResource {
	@Autowired	
	private ComputerUserService service;
	
	/**
	 * Finds all computer users
	 * @return ResponseEntity<List<ComputerUserDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<ComputerUserDTO>> findAll() {
		List<ComputerUser> objects = this.service.findAll();
		List<ComputerUserDTO> objectsDTO = objects.stream().map(
				obj -> new ComputerUserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds all computer users per page
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @return ResponseEntity<Page<ComputerUserDTO>>
	 */
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
	
	/**
	 * Finds the computer with its ID.
	 * @param id
	 * @return ResponseEntity<ComputerUser>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ComputerUser> findById(@PathVariable Long id) {
		ComputerUser object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	/**
	 * Generalized search method
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<ComputerUserDTO>>
	 */
	@GetMapping("/search")
    public ResponseEntity<Page<ComputerUserDTO>> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
    		@RequestParam("searchTerm") String searchTerm) {
		Page<ComputerUser> objects = this.service.search(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<ComputerUserDTO> objectsDTO = objects.map(obj -> new ComputerUserDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }
	
	/**
	 * Inserts a new computer.
	 * @param objectDTO
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ComputerUserNewDTO objectDTO) {
		ComputerUser object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Deletes the computer user referred by id.
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Updates the computer user referred by id.
	 * @param objectDTO
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ComputerUserNewDTO objectDTO, @PathVariable Long id) {
		ComputerUser object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	/**
	 * Generates the computer users report.
	 * @return ResponseEntity<InputStreamResource>
	 */
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> computerUsersReport() {

		List<ComputerUser> computer_users = (List<ComputerUser>) service.findAll();

        ByteArrayInputStream bis = GeneratePdfReportFromComputerUser.computerUsersReport(computer_users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=computer_users_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
	
}

/** 
 * This is the class "MonitorResource". Which will be to represent a REST controller of Monitor model.
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

import com.pml.domain.Monitor;
import com.pml.dto.MonitorDTO;
import com.pml.dto.MonitorNewDTO;
import com.pml.services.MonitorService;
import com.pml.util.GeneratePdfReportFromMonitor;

@RestController
@RequestMapping(value = "/api/monitors")
public class MonitorResource {
	@Autowired	
	private MonitorService service;
	
	/**
	 * Finds all monitors.
	 * @return ResponseEntity<List<MonitorDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<MonitorDTO>> findAll() {
		List<Monitor> objects = this.service.findAll();
		List<MonitorDTO> objectsDTO = objects.stream().map(
				obj -> new MonitorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds all available monitors (without computer).
	 * @return ResponseEntity<List<MonitorDTO>>
	 */
	@GetMapping("/available")
	public ResponseEntity<List<MonitorDTO>> findAllWithoutComputer() {
		List<Monitor> objects = this.service.findAllWithoutComputer();
		List<MonitorDTO> objectsDTO = objects.stream().map(
				obj -> new MonitorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds all monitors per page.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @return ResponseEntity<Page<MonitorDTO>>
	 */
	@GetMapping("/page")
	public ResponseEntity<Page<MonitorDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy) {
		
		Page<Monitor> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<MonitorDTO> objectsDTO = objects.map(obj -> new MonitorDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds the monitor with its ID.
	 * @param id
	 * @return ResponseEntity<Monitor>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Monitor> findById(@PathVariable Long id) {
		Monitor object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	/**
	 * Generalized search method.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<MonitorDTO>>
	 */
	@GetMapping("/search")
    public ResponseEntity<Page<MonitorDTO>> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
    		@RequestParam("searchTerm") String searchTerm) {
		Page<Monitor> objects = this.service.search(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<MonitorDTO> objectsDTO = objects.map(obj -> new MonitorDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }

	/**
	 * Inserts a new monitor.
	 * @param objectDTO
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody MonitorNewDTO objectDTO) {
		Monitor object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Deletes the monitor referred by id.
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
	 * Updates the monitor referred by id.
	 * @param objectDTO
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody MonitorNewDTO objectDTO, @PathVariable Long id) {
		Monitor object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	/**
	 * Generates the monitors report.
	 * @return ResponseEntity<InputStreamResource>
	 */
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> monitorsReport() {

		List<Monitor> monitors = (List<Monitor>) service.findAll();

        ByteArrayInputStream bis = GeneratePdfReportFromMonitor.monitorsReport(monitors);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=monitors_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
	
}

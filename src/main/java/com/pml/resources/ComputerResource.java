/** 
 * This is the class "ComputerResource". Which will be to represent a REST controller of Computer model.
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

import com.pml.domain.Computer;
import com.pml.dto.ComputerDTO;
import com.pml.dto.ComputerNewDTO;
import com.pml.services.ComputerService;
import com.pml.util.GeneratePdfReportFromComputer;

@RestController
@RequestMapping(value = "/api/computers")
public class ComputerResource {
	@Autowired	
	private ComputerService service;
	
	/**
	 * Finds all computers.
	 * @return ResponseEntity<List<ComputerDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<ComputerDTO>> findAll() {
		List<Computer> objects = this.service.findAll();
		List<ComputerDTO> objectsDTO = objects.stream().map(
				obj -> new ComputerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds all available computers (without monitor).
	 * @return ResponseEntity<List<ComputerDTO>>
	 */
	@GetMapping("/available")
	public ResponseEntity<List<ComputerDTO>> findAllWithoutMonitor() {
		List<Computer> objects = this.service.findAllWithoutMonitor();
		List<ComputerDTO> objectsDTO = objects.stream().map(
				obj -> new ComputerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds all computers per page.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @return ResponseEntity<Page<ComputerDTO>>
	 */
	@GetMapping("/page")
	public ResponseEntity<Page<ComputerDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy) {		
		Page<Computer> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds the computer with its ID.
	 * @param id
	 * @return ResponseEntity<Computer>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Computer> findById(@PathVariable Long id) {
		Computer object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	/**
	 * Generalized search method.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<ComputerDTO>>
	 */
	@GetMapping("/search")
    public ResponseEntity<Page<ComputerDTO>> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
            @RequestParam(value = "searchTerm") String searchTerm) {
		Page<Computer> objects = this.service.search(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }
	
	/**
	 * Search for computers by processor terms, such as manufacturer, model, and processor name.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<ComputerDTO>>
	 */
	@GetMapping("/search/processor_terms")
    public ResponseEntity<Page<ComputerDTO>> searchByProcessorTerms(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
            @RequestParam(value = "searchTerm") String searchTerm) {
		Page<Computer> objects = this.service.searchByProcessorTerms(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }
	
	/**
	 * Search for computers by computer user terms, such as name and last name.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<ComputerDTO>>
	 */	
	@GetMapping("/search/computer_user_terms")
    public ResponseEntity<Page<ComputerDTO>> searchByComputerUserTerms(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
            @RequestParam(value = "searchTerm") String searchTerm) {
		Page<Computer> objects = this.service.searchByComputerUserTerms(page, linesPerPage, direction, orderBy, searchTerm);
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }

	/**
	 * Searches for computers using the online attribute, whether true or false.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<ComputerDTO>>
	 */
	@GetMapping("/search/online")
    public ResponseEntity<Page<ComputerDTO>> searchByOnline(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
            @RequestParam(value = "searchTerm") boolean searchTerm) {
		Page<Computer> objects = this.service.searchByOnline(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }

	/**
	 * Searches for computers using the on the domain attribute, whether true or false.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<ComputerDTO>>
	 */
	@GetMapping("/search/on_the_domain")
    public ResponseEntity<Page<ComputerDTO>> searchByOnTheDomain(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
            @RequestParam(value = "searchTerm") boolean searchTerm) {
		Page<Computer> objects = this.service.searchByOnTheDomain(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }
	
	/**
	 * Searches for computers using the personal computer attribute, whether true or false.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<ComputerDTO>>
	 */	
	@GetMapping("/search/personal_computer")
    public ResponseEntity<Page<ComputerDTO>> searchByPersonalComputer(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
            @RequestParam(value = "searchTerm") boolean searchTerm) {
		Page<Computer> objects = this.service.searchByPersonalComputer(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<ComputerDTO> objectsDTO = objects.map(obj -> new ComputerDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }
	
	/**
	 * Inserts a new computer.
	 * @param objectDTO
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ComputerNewDTO objectDTO) {
		Computer object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Deletes the computer referred by id.
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
	 * Updates the computer referred by id.
	 * @param objectDTO
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ComputerNewDTO objectDTO, @PathVariable Long id) {
		Computer object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	/**
	 * Generates the computers report.
	 * @return ResponseEntity<InputStreamResource>
	 */
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> computersReport() {

		List<Computer> computers = (List<Computer>) service.findAll();

        ByteArrayInputStream bis = GeneratePdfReportFromComputer.computersReport(computers);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=computers_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
	
}

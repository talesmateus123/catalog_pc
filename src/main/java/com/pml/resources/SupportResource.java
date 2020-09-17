/** 
 * This is the class "ComputerResource". Which will be to represent a REST controller of Computer model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pml.domain.Computer;
import com.pml.dto.ComputerUserSupportDTO;
import com.pml.services.SupportService;
import com.pml.util.GeneratePdfReportFromComputer;

@RestController
@RequestMapping(value = "/api/support")
public class SupportResource {
	@Autowired	
	private SupportService service;
	
	/**
	 * Finds all computers.
	 * @return ResponseEntity<List<ComputerDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<ComputerUserSupportDTO>> findAll() {
		List<Computer> objects = this.service.findAll();
		List<ComputerUserSupportDTO> objectsDTO = objects.stream().map(
				obj -> new ComputerUserSupportDTO(obj)).collect(Collectors.toList());
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
	public ResponseEntity<Page<ComputerUserSupportDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy) {		
		Page<Computer> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<ComputerUserSupportDTO> objectsDTO = objects.map(obj -> new ComputerUserSupportDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds the computer with its ID.
	 * @param id
	 * @return ResponseEntity<ComputerUserSupportDTO>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ComputerUserSupportDTO> findById(@PathVariable Long id) {
		Computer object = this.service.findById(id);
		ComputerUserSupportDTO objectDTO = new ComputerUserSupportDTO(object);
		return ResponseEntity.ok().body(objectDTO);
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
    public ResponseEntity<Page<ComputerUserSupportDTO>> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
            @RequestParam(value = "searchTerm") String searchTerm) {
		Page<Computer> objects = this.service.search(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<ComputerUserSupportDTO> objectsDTO = objects.map(obj -> new ComputerUserSupportDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
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

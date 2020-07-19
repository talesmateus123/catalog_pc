/** 
 * This is the class "PrinterResource". Which will be to represent a REST controller of Printer model.
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

import com.pml.domain.Printer;
import com.pml.dto.PrinterDTO;
import com.pml.dto.PrinterNewDTO;
import com.pml.services.PrinterService;
import com.pml.util.GeneratePdfReportFromPrinter;

@RestController
@RequestMapping(value = "/api/printers")
public class PrinterResource {
	@Autowired	
	private PrinterService service;
	
	@GetMapping
	public ResponseEntity<List<PrinterDTO>> findAll() {
		List<Printer> objects = this.service.findAll();
		List<PrinterDTO> objectsDTO = objects.stream().map(
				obj -> new PrinterDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<PrinterDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "motherBoardName") String orderBy) {
		
		Page<Printer> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<PrinterDTO> objectsDTO = objects.map(obj -> new PrinterDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Printer> findById(@PathVariable Long id) {
		Printer object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping("/patrimonyId/{patrimonyId}")
	public ResponseEntity<Printer> findByPatrimonyId(@PathVariable String patrimonyId) {
		Printer object = this.service.findByPatrimonyId(patrimonyId);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping("/search")
    public Page<Printer> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
    		@RequestParam("searchTerm") String searchTerm) {
        return service.search(page, linesPerPage, direction, orderBy, searchTerm);
    }

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody PrinterNewDTO objectDTO) {
		Printer object = this.service.fromDTO(objectDTO);
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
	public ResponseEntity<Void> update(@Valid @RequestBody PrinterNewDTO objectDTO, @PathVariable Long id) {
		Printer object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        var printers = (List<Printer>) service.findAll();

        ByteArrayInputStream bis = GeneratePdfReportFromPrinter.printersReport(printers);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=printers_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
	
}

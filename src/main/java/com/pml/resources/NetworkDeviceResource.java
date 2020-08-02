/** 
 * This is the class "NetworkDeviceResource". Which will be to represent a REST controller of NetworkDevice model.
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

import com.pml.domain.NetworkDevice;
import com.pml.dto.NetworkDeviceDTO;
import com.pml.dto.NetworkDeviceNewDTO;
import com.pml.services.NetworkDeviceService;
import com.pml.util.GeneratePdfReportFromNetworkDevice;

@RestController
@RequestMapping(value = "/api/network_devices")
public class NetworkDeviceResource {
	@Autowired	
	private NetworkDeviceService service;
	
	/**
	 * Finds all networkDevices.
	 * @return ResponseEntity<List<NetworkDeviceDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<NetworkDeviceDTO>> findAll() {
		List<NetworkDevice> objects = this.service.findAll();
		List<NetworkDeviceDTO> objectsDTO = objects.stream().map(
				obj -> new NetworkDeviceDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds all networkDevices per page.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @return ResponseEntity<Page<NetworkDeviceDTO>>
	 */
	@GetMapping("/page")
	public ResponseEntity<Page<NetworkDeviceDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy) {
		
		Page<NetworkDevice> objects = this.service.findPage(page, linesPerPage, direction, orderBy);
		Page<NetworkDeviceDTO> objectsDTO = objects.map(obj -> new NetworkDeviceDTO(obj));
		return ResponseEntity.ok().body(objectsDTO);
	}
	
	/**
	 * Finds the networkDevice with its ID.
	 * @param id
	 * @return ResponseEntity<NetworkDevice>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<NetworkDevice> findById(@PathVariable Long id) {
		NetworkDevice object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	/**
	 * Generalized search method.
	 * @param page
	 * @param linesPerPage
	 * @param direction
	 * @param orderBy
	 * @param searchTerm
	 * @return ResponseEntity<Page<NetworkDeviceDTO>>
	 */
	@GetMapping("/search")
    public ResponseEntity<Page<NetworkDeviceDTO>> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction, 
            @RequestParam(value = "orderBy", defaultValue = "patrimonyId") String orderBy,
    		@RequestParam("searchTerm") String searchTerm) {
		Page<NetworkDevice> objects = this.service.search(page, linesPerPage, direction, orderBy, searchTerm);		
		Page<NetworkDeviceDTO> objectsDTO = objects.map(obj -> new NetworkDeviceDTO(obj));		
        return ResponseEntity.ok().body(objectsDTO);
    }

	/**
	 * Inserts a new networkDevice.
	 * @param objectDTO
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody NetworkDeviceNewDTO objectDTO) {
		NetworkDevice object = this.service.fromDTO(objectDTO);
		object = this.service.insert(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{patrimonyId}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Deletes the networkDevice referred by id.
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
	 * Updates the networkDevice referred by id.
	 * @param objectDTO
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody NetworkDeviceNewDTO objectDTO, @PathVariable Long id) {
		NetworkDevice object = this.service.fromDTO(objectDTO);
		object.setId(id);
		
		this.service.update(object);
		return ResponseEntity.noContent().build();		
	}
	
	/**
	 * Generates the networkDevices report.
	 * @return ResponseEntity<InputStreamResource>
	 */
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> networkDevicesReport() {

        var networkDevices = (List<NetworkDevice>) service.findAll();

        ByteArrayInputStream bis = GeneratePdfReportFromNetworkDevice.networkDevicesReport(networkDevices);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=networkDevices_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
	
}

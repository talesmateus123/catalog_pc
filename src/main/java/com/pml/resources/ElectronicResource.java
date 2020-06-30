/** 
 * This is the class "ComputerResource". Which will be to represent a REST controller of Computer model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pml.domain.Electronic;
import com.pml.services.ElectronicService;

@RestController
@RequestMapping(value = "/api/electronics")
public class ElectronicResource {
	@Autowired	
	private ElectronicService service;

	@GetMapping("/{id}")
	public ResponseEntity<Electronic> findById(@PathVariable Long id) {
		Electronic object = this.service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	
	@GetMapping("/search")
    public Page<Electronic> search(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam("direction") String direction, 
            @RequestParam("orderBy") String orderBy,
    		@RequestParam("searchTermManufacturer") String searchTermManufacturer, 
    		@RequestParam("searchTermModel") String searchTermModel, 
    		@RequestParam("equipmentType") String equipmentType) {
        return service.search(page, linesPerPage, direction, orderBy, searchTermManufacturer, searchTermModel, equipmentType);
    }
	
	
	
}

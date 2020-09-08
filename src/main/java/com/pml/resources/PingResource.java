/** 
 * This is the class "SectorResource". Which will be to represent a REST controller of Sector model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ping")
public class PingResource {
	
	@GetMapping
	public ResponseEntity<Void> ping() {
		return ResponseEntity.noContent().build();
	}
	
	
	
}

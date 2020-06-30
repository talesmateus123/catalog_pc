/** 
 * This is the class "EquipmentService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Electronic;
import com.pml.repositories.ElectronicRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ElectronicService {
	@Autowired
	private ElectronicRepository repository;
		
	public Electronic findById(Long id) {
		Optional<Electronic> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Electronic not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Page<Electronic> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTermManufacturer, String searchTermModel, String equipmentType) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy, "name");
        return repository.search(searchTermManufacturer.toLowerCase(), searchTermModel.toLowerCase(), equipmentType, pageRequest);
    }
	
	
	
}

/** 
 * This is the class "SectorService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Sector;
import com.pml.dto.SectorNewDTO;
import com.pml.repositories.SectorRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class SectorService {
	@Autowired
	private SectorRepository repository;
	
	// List search methods
	public List<Sector> findAll() {
		return this.repository.findByOrderByName();
	}
	
	public Page<Sector> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}

	// Simple search methods
	public Sector findById(Integer id) {
		Optional<Sector> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Sector not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Sector findByName(String name) {
		Optional<Sector> object = this.repository.findByName(name);
		return object.orElseThrow(()-> new ObjectNotFoundException("Sector not found: name: '" + name + "'. Type: " + object.getClass().getName()));
	}
	
	public Page<Sector> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return repository.search(searchTerm.toLowerCase(), pageRequest);
    }

	// Create, update and delete methods
	@Transactional
	public Sector insert(Sector object) {
		object.setId(null);
		return this.repository.save(object);
	}

	public void delete(Integer id) {
		this.findById(id);				
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the object: id: '" + id + "'. This object has still dependents.");
		}
		
	}

	public Sector update(Sector object) {
		return this.repository.saveAndFlush(object);
	}

	// Auxiliary methods	
	/**
	 * Convert the SectorNewDTO object to a Sector object. 
	 * @param objectNewDTO SectorNewDTO
	 * @return Sector
	 */
	public Sector fromDTO(SectorNewDTO objectNewDTO) {
		Sector object = new Sector(null, objectNewDTO.getName());
		
		return object;
	}
	
	
	
}

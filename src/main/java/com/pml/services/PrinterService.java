/** 
 * This is the class "PrinterService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pml.domain.Equipment;
import com.pml.domain.Printer;
import com.pml.dto.PrinterNewDTO;
import com.pml.repositories.EquipmentRepository;
import com.pml.repositories.PrinterRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class PrinterService {
	@Autowired
	private PrinterRepository repository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private SectorService sectorService;

	// List search methods
	public List<Printer> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Printer> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public Printer findByPatrimonyId(String patrimonyId) {
		Optional<Printer> object = this.repository.findByPatrimonyId(patrimonyId);
		return object.orElseThrow(()-> new ObjectNotFoundException("Printer not found: patrimonyId: '" + patrimonyId + "'. Type: " + object.getClass().getName()));
	}
	
	public Printer findById(Long id) {
		Optional<Printer> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Printer not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Printer insert(Printer object) {
		if(alreadyExists(object.getPatrimonyId())){
			throw new ConflictOfObjectsException("This machine already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		object.setId(null);
		object.setCreatedDate(new Date());
		return this.repository.save(object);
	}

	public void delete(Long id) {
		this.findById(id);
		this.repository.deleteById(id);
	}

	public Printer update(Printer object) {
		recoverData(object);
		if(patrimonyIdIsChanged(object)){
			if(alreadyExists(object.getPatrimonyId()))
				throw new ConflictOfObjectsException("This machine already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		return this.repository.saveAndFlush(object);		
	}
	
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object Printer
	 * @return void
	 */
	private void recoverData(Printer object) {
		Printer oldObject = this.findById(object.getId());
		object.setCreatedDate(oldObject.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}
	
	/**
	 * Verify if already exists the patrimony id requested.
	 * @param patrimonyId Long
	 * @return boolean
	 */
	private boolean alreadyExists(String patrimonyId) {	
		Optional<Equipment> objectByPatrimonyId = this.equipmentRepository.findByPatrimonyId(patrimonyId);
		
		if(objectByPatrimonyId.isEmpty())
			return false; 
		return true;
	}
	
	/**
	 * Verify if the object in a question has its patrimony id changed.
	 * @param object Printer
	 * @return boolean
	 */
	private boolean patrimonyIdIsChanged(Printer object) {	
		Optional<Printer> objectByPatrimonyId = this.repository.findByPatrimonyId(object.getPatrimonyId());	
		// Generates an exception if object doesn't exists 
		Printer objectById = this.findById(object.getId());
		
		if(objectByPatrimonyId.isEmpty())
			return true;
		
		if(objectById.getPatrimonyId().equals(objectByPatrimonyId.get().getPatrimonyId()))
			return false;		
		return true;
	}
	
	/**
	 * Convert the PrinterNewDTO object to a Printer object. 
	 * @param objectNewDTO PrinterDTO
	 * @return Printer
	 */
	public Printer fromDTO(PrinterNewDTO objectNewDTO) {		
		Printer object = new Printer(
				null, objectNewDTO.getPatrimonyId(), null, null,
				objectNewDTO.getManufacturer(), objectNewDTO.getModel(), objectNewDTO.getDescription(), 
				null, objectNewDTO.isItWorks(), objectNewDTO.getIpAddress(), 
				objectNewDTO.getHostName());	
		if(objectNewDTO.getSectorId() != null)
			object.setSector(this.sectorService.findById(objectNewDTO.getSectorId()));	
		
		return object;
	}
	
	
}

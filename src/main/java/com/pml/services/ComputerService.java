/** 
 * This is the class "ComputerService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.dto.ComputerDTO;
import com.pml.repositories.ComputerRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ComputerService {
	@Autowired
	private ComputerRepository repository;
	
	public List<Computer> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Computer> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public Computer find(String patrimonyId) {
		Optional<Computer> object = this.repository.findByPatrimonyId(patrimonyId);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer not found: " + patrimonyId + ". Type: " + object.getClass().getName()));
	}
	
	public Computer insert(Computer object) {
		alreadyExists(object);
		object.setId(null);
		object.setCreatedDate(new Date());
		return this.repository.save(object);
	}

	public void delete(String patrimonyId) {
		Computer objetc = this.find(patrimonyId);
		try {
			this.repository.deleteById(objetc.getId());
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the computer: " + patrimonyId + ". This computer has still dependents.");
		}
	}

	public Computer update(Computer object) {
		Computer objetcX = this.find(object.getPatrimonyId());
		object.setId(objetcX.getId());
		object.setCreatedDate(objetcX.getCreatedDate());
		object.setModifiedDate(new Date());
		return this.repository.saveAndFlush(object);
		
	}
	
	public void alreadyExists(Computer object) {
		Optional<Computer> objectX = this.repository.findByPatrimonyId(object.getPatrimonyId());
		if(objectX.isEmpty()) {
			return ; 
		}
		throw new ConflictOfObjectsException("This computer already exists: " + object.getPatrimonyId() + ".");
	}
	
	public Computer fromDTO(ComputerDTO computerDTO) {
		return new Computer(
				computerDTO.getPatrimonyId(), computerDTO.getCreatedDate(), computerDTO.getLastModifiedDate(),
				computerDTO.getManufacturer(), computerDTO.getModel(), computerDTO.getDescription(), 
				computerDTO.getSector().getCod(), computerDTO.isItWorks(), computerDTO.getIpAddress(), computerDTO.getMotherBoardName(), 
				computerDTO.getMemoryType().getCod(), computerDTO.getMemorySize(),  computerDTO.getHdType().getCod(),	
				computerDTO.getHdSize(),  computerDTO.getProcessorModel(), computerDTO.getProcessorArchitecture().getCod(), 
				computerDTO.getHasCdBurner(), computerDTO.getCabinetModel(), computerDTO.getOperatingSystem().getCod(),
				computerDTO.getOperatingSystemArchitecture().getCod(), computerDTO.isOnTheDomain(), null);
	}
	
	
}

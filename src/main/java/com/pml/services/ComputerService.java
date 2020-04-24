/** 
 * This is the class "ComputerService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.dto.ComputerDTO;
import com.pml.dto.ComputerNewDTO;
import com.pml.repositories.ComputerRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ComputerService {
	@Autowired
	private ComputerRepository repository;
	@Autowired
	private ComputerUserService computerUserService;
	@Autowired
	private MonitorService monitorService;
	@Autowired
	private ProcessorService processorService;
	@Autowired
	private RamMemoryService ramMemoryService;
	@Autowired
	private StorageDeviceService storageDeviceService;
	
	public List<Computer> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Computer> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public Computer findByPatrimonyId(String patrimonyId) {
		Optional<Computer> object = this.repository.findByPatrimonyId(patrimonyId);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer not found: patrimonyId: '" + patrimonyId + "'. Type: " + object.getClass().getName()));
	}
	
	public Computer findById(Long id) {
		Optional<Computer> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	@Transactional
	public Computer insert(Computer object) {
		if(alreadyExists(object.getPatrimonyId())){
			throw new ConflictOfObjectsException("This computer already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		object.setId(null);
		object.setCreatedDate(new Date());
		
		// return object;
		
		return this.repository.save(object);
	}

	public void delete(Long id) {
		Computer objetc = this.findById(id);
		try {
			this.repository.deleteById(objetc.getId());
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the computer: id: '" + id + "'. This computer has still dependents.");
		}
	}

	@Transactional
	public Computer update(Computer object) {
		if(patrimonyIdIsChanged(object)){
			if(alreadyExists(object.getPatrimonyId()))
				throw new ConflictOfObjectsException("This computer already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		
		recoverData(object);
		return this.repository.saveAndFlush(object);		
	}
	
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object
	 * @return void
	 */
	private void recoverData(Computer object) {
		Computer oldObject = this.findById(object.getId());
		object.setCreatedDate(oldObject.getCreatedDate());
		object.setLastModifiedDate(new Date());
	}
	
	/**
	 * Verify if already exists the patrimony id requested.
	 * @param Long patrimonyId
	 * @return boolean
	 */
	private boolean alreadyExists(String patrimonyId) {	
		Optional<Computer> objectByPatrimonyId = this.repository.findByPatrimonyId(patrimonyId);
		
		if(objectByPatrimonyId.isEmpty())
			return false; 
		return true;
	}
	
	/**
	 * Verify if the object in a question has its patrimony id changed.
	 * @param object
	 * @return boolean
	 */
	private boolean patrimonyIdIsChanged(Computer object) {	
		Optional<Computer> objectByPatrimonyId = this.repository.findByPatrimonyId(object.getPatrimonyId());	
		// Generates an exception if object doesn't exists 
		Computer objectById = this.findById(object.getId());
		
		if(objectByPatrimonyId.isEmpty())
			return true;
		
		if(objectById.getPatrimonyId().equals(objectByPatrimonyId.get().getPatrimonyId()))
			return false;		
		return true;
	}
	
	/**
	 * Convert the ComputerDTO object to a Computer object. 
	 * @param computerDTO ComputerDTO
	 * @return Computer
	 */
	public Computer fromDTO(ComputerDTO computerDTO) {
		Computer computer = new Computer(
				computerDTO.getId(), computerDTO.getPatrimonyId(), computerDTO.getCreatedDate(), 
				computerDTO.getLastModifiedDate(), computerDTO.getManufacturer(), computerDTO.getModel(), 
				computerDTO.getDescription(), computerDTO.getSector(), computerDTO.isItWorks(), 
				computerDTO.getIpAddress(), computerDTO.getHostName(), computerDTO.getMotherBoardName(),  null,  
				computerDTO.getHasCdBurner(), computerDTO.getCabinetModel(), computerDTO.getOperatingSystem(),
				computerDTO.getOperatingSystemArchitecture(), computerDTO.isOnTheDomain(), null);
		return computer;
	}
	
	/**
	 * Convert the ComputerNewDTO object to a Computer object. 
	 * @param computerDTO ComputerDTO
	 * @return Computer
	 */
	public Computer fromDTO(ComputerNewDTO computerNewDTO) {		
		Computer computer = new Computer(
				null, computerNewDTO.getPatrimonyId(), null, null,
				computerNewDTO.getManufacturer(), computerNewDTO.getModel(), computerNewDTO.getDescription(), 
				computerNewDTO.getSector(), computerNewDTO.isItWorks(), computerNewDTO.getIpAddress(), 
				computerNewDTO.getHostName(), computerNewDTO.getMotherBoardName(), null, computerNewDTO.getHasCdBurner(),
				 computerNewDTO.getCabinetModel(), computerNewDTO.getOperatingSystem(),
				computerNewDTO.getOperatingSystemArchitecture(), computerNewDTO.isOnTheDomain(), null);
		
		// Setting all attributes
		if(computerNewDTO.getMonitorId() != null)
			computer.setMonitor(this.monitorService.findById(computerNewDTO.getMonitorId()));
		if(computerNewDTO.getProcessorId() != null)
			computer.setProcessor(this.processorService.findById(computerNewDTO.getProcessorId()));
		
		for(Long ramMemoryId : computerNewDTO.getRamMemoriesId()) {
			computer.addRamMemory(this.ramMemoryService.findById(ramMemoryId));
		}
		for(Long storageDeviceId : computerNewDTO.getStorageDevicesId()) {
			computer.addStorageDevice(this.storageDeviceService.findById(storageDeviceId));
		}
		for(Long computerUserId : computerNewDTO.getComputerUsersId()) {
			computer.addComputerUser(this.computerUserService.findById(computerUserId));
		}
		
		return computer;
	}
	
	
}

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
import com.pml.domain.ComputerUser;
import com.pml.domain.Equipment;
import com.pml.domain.Monitor;
import com.pml.domain.RamMemory;
import com.pml.domain.StorageDevice;
import com.pml.dto.ComputerNewDTO;
import com.pml.repositories.ComputerRepository;
import com.pml.repositories.EquipmentRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ComputerService {
	@Autowired
	private ComputerRepository repository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private ComputerUserService computerUserService;
	@Autowired
	private MonitorService monitorService;
	@Autowired
	private ProcessorService processorService;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private RamMemoryService ramMemoryService;
	@Autowired
	private StorageDeviceService storageDeviceService;

	// List search methods
	public List<Computer> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Computer> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public List<Computer> findByManufacturer(String manufacturer) {
		return this.repository.findByManufacturer(manufacturer);
	}

	// Simple search methods
	public Computer findById(Long id) {
		Optional<Computer> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	public Computer findByPatrimonyId(String patrimonyId) {
		Optional<Computer> object = this.repository.findByPatrimonyId(patrimonyId);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer not found: patrimonyId: '" + patrimonyId + "'. Type: " + object.getClass().getName()));
	}
	
	public Computer findByIpAddress(String ipAddress) {
		Optional<Computer> object = this.repository.findByIpAddress(ipAddress);
		return object.orElseThrow(()-> new ObjectNotFoundException("This ipAddress: '" + ipAddress + "'has no computer. Type: " + object.getClass().getName()));
	}
	
	public Computer findByMonitor(Monitor monitor) {
		Optional<Computer> object = this.repository.findByMonitor(monitor);
		return object.orElseThrow(()-> new ObjectNotFoundException("This monitor: patrimonyId: '" + monitor.getPatrimonyId() + "'has no computer. Type: " + object.getClass().getName()));
	}

	// Create, update and delete methods
	@Transactional
	public Computer insert(Computer object) {
		if(alreadyExists(object.getPatrimonyId())){
			throw new ConflictOfObjectsException("This machine already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		object.setId(null);
		object.setCreatedDate(new Date());
		return this.repository.save(object);
	}

	public void delete(Long id) {
		this.findById(id);
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the object: id: '" + id + "'. This object has still dependents.");
		}
	}

	@Transactional
	public Computer update(Computer object) {
		recoverData(object);
		if(patrimonyIdIsChanged(object)){
			if(alreadyExists(object.getPatrimonyId()))
				throw new ConflictOfObjectsException("This machine already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		return this.repository.saveAndFlush(object);
	}

	// Auxiliary methods
	/**
	 * Recover data of created date and updates the last modified date.
	 * @param object Computer
	 * @return void
	 */
	private void recoverData(Computer object) {
		Computer oldObject = this.findById(object.getId());
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
	 * @param object Computer
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
	 * Convert the ComputerNewDTO object to a Computer object. 
	 * @param objectDTO ComputerDTO
	 * @return Computer
	 */
	public Computer fromDTO(ComputerNewDTO objectNewDTO) {		
		Computer object = new Computer(
				null, objectNewDTO.getPatrimonyId(), null, null,
				objectNewDTO.getManufacturer(), objectNewDTO.getModel(), objectNewDTO.getDescription(), 
				null, objectNewDTO.isItWorks(), objectNewDTO.getIpAddress(), 
				objectNewDTO.getHostName(), objectNewDTO.getMotherBoardName(), null, objectNewDTO.getHasCdBurner(),
				 objectNewDTO.getCabinetModel(), objectNewDTO.getOperatingSystem(),
				objectNewDTO.getOperatingSystemArchitecture(), objectNewDTO.isOnTheDomain(), null);
		
		// Setting all attributes
		if(objectNewDTO.getMonitorId() != null) {
			object.setMonitor(this.monitorService.findById(objectNewDTO.getMonitorId()));
		}
		if(objectNewDTO.getProcessorId() != null) {
			object.setProcessor(this.processorService.findById(objectNewDTO.getProcessorId()));
		}
		if(objectNewDTO.getSectorId() != null) {
			object.setSector(this.sectorService.findById(objectNewDTO.getSectorId()));
		}
			
		if(objectNewDTO.getRamMemoriesId() != null) {
			for(Long ramMemoryId : objectNewDTO.getRamMemoriesId()) {
				RamMemory ramMemory = this.ramMemoryService.findById(ramMemoryId);
				ramMemory.setComputer(object);
				object.addRamMemory(ramMemory);
			}
		}
		
		if(objectNewDTO.getStorageDevicesId() != null) {
			for(Long storageDeviceId : objectNewDTO.getStorageDevicesId()) {
				StorageDevice storageDevice = this.storageDeviceService.findById(storageDeviceId);
				storageDevice.setComputer(object);
				object.addStorageDevice(storageDevice);
			}
		}
		if(objectNewDTO.getComputerUsersId() != null) {
			for(Long computerUserId : objectNewDTO.getComputerUsersId()) {
				ComputerUser computerUser = this.computerUserService.findById(computerUserId);
				computerUser.addUseTheComputer(object);
				object.addComputerUser(computerUser);
			}
		}		
		return object;
	}
	
	
}

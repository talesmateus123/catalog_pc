/** 
 * This is the class "ComputerService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.domain.ComputerUser;
import com.pml.domain.Monitor;
import com.pml.domain.Processor;
import com.pml.domain.RamMemory;
import com.pml.domain.StorageDevice;
import com.pml.dto.ComputerNewDTO;
import com.pml.repositories.ComputerRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

@Service
public class ComputerService extends EquipmentService {
	@Autowired
	private ComputerRepository repository;
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
		return this.repository.findByOrderByPatrimonyId();
	}
	
	public List<Computer> findAllWithoutMonitor() {
		return this.repository.findAllByMonitorNull();
	}
	
	public Page<Computer> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public List<Computer> findByManufacturer(String manufacturer) {
		return this.repository.findByManufacturer(manufacturer);
	}

	// Simple search methods
	@Override
	public Computer findById(Long id) {
		Optional<Computer> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	@Override
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
	
	public Page<Computer> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {		
    	try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, Computer.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + Computer.class.getName() + "' class.");
        	return repository.search(searchTerm.toLowerCase(), pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The	 value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }

	// Create, update and delete methods
	public Computer save(Computer object) {
		if(object.getId() == null)
			return this.insert(object);
		return this.update(object);
	}
	
	@Transactional
	public Computer insert(Computer object) {
		if(this.alreadyExists(object.getPatrimonyId())){
			throw new ConflictOfObjectsException("This equipment already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		object.setId(null);
		object.setCreatedDate(new Date());
		updateReferences(object);

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
		this.recoverData(object);
		if(this.patrimonyIdIsChanged(object)){
			if(this.alreadyExists(object.getPatrimonyId()))
				throw new ConflictOfObjectsException("This equipment already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
		}
		
		Processor processor = object.getProcessor();
		List<RamMemory> ramMemories = object.getRamMemories();
		List<StorageDevice> storageDevices = object.getStorageDevices();
		
		updateOldReferences(object);
		
		object.setRamMemories(new ArrayList<>());
		object.setStorageDevices(new ArrayList<>());
		
		object = this.repository.saveAndFlush(object);
		
		object.setProcessor(processor);
		object.setRamMemories(ramMemories);
		object.setStorageDevices(storageDevices);
		
		updateReferences(object);
		
		return object;
	}

	// Auxiliary methods
	private void updateOldReferences(Computer object) {
		Computer oldObject = (object.getId() != null) ? this.findById(object.getId()) : null;
		
		// Setting the old object
		if(oldObject != null) {
			for(RamMemory ramMemory : oldObject.getRamMemories()) {
				if(!object.getRamMemories().contains(ramMemory)) {
					ramMemory.setComputer(null);
					this.ramMemoryService.save(ramMemory);
				}
			}
			for(StorageDevice storageDevice : oldObject.getStorageDevices()) {
				if(!object.getStorageDevices().contains(storageDevice)) {
					storageDevice.setComputer(null);
					this.storageDeviceService.save(storageDevice);
				}
			}
		}
	}
	
	private void updateReferences(Computer object) {
		if(object.getProcessor() != null) {
			Processor processor = this.processorService.save(object.getProcessor());
			processor.setComputer(object);
			object.setProcessor(processor);
		}
		
		for (RamMemory ramMemory: object.getRamMemories()) {	
			ramMemory.setComputer(object);
			object.addRamMemory(this.ramMemoryService.save(ramMemory));
		}
		
		for (StorageDevice storageDevice: object.getStorageDevices()) {
			storageDevice.setComputer(object);
			object.addStorageDevice(this.storageDeviceService.save(storageDevice));
		}
	}

	/**
	 * Convert the ComputerNewDTO object to a Computer object. 
	 * @param objectDTO ComputerDTO
	 * @return Computer
	 */
	public Computer fromDTO(ComputerNewDTO objectNewDTO) {
		// Setting all attributes
		Computer object = new Computer(
				null, objectNewDTO.getPatrimonyId(), null, null,
				objectNewDTO.getManufacturer(), objectNewDTO.getModel(), objectNewDTO.getDescription(), 
				null, objectNewDTO.isItWorks(), objectNewDTO.getIpAddress(), 
				objectNewDTO.getHostName(), objectNewDTO.getMotherBoardName(), null, objectNewDTO.getHasCdBurner(),
				 objectNewDTO.getCabinetModel(), objectNewDTO.getOperatingSystem(), objectNewDTO.getOperatingSystemArchitecture(), 
				 objectNewDTO.isOnTheDomain(), objectNewDTO.getTotalRamMemory(), objectNewDTO.getTotalStorageMemory(), null);
		
		// One to one relationships
		if(objectNewDTO.getMonitorId() != null)
			object.setMonitor(this.monitorService.findById(objectNewDTO.getMonitorId()));
		
		if(objectNewDTO.getSectorId() != null)
			object.setSector(this.sectorService.findById(objectNewDTO.getSectorId()));
		
		if(objectNewDTO.getProcessor_id() != null || objectNewDTO.getProcessor_manufacturer() != null || objectNewDTO.getProcessor_model() != null ||
		objectNewDTO.getProcessor_description() != null || objectNewDTO.isProcessor_itWorks() != null || objectNewDTO.getProcessor_processorName() != null || objectNewDTO.getProcessor_architecture() != null)
			object.setProcessor(new Processor(objectNewDTO.getProcessor_id(), null, null, objectNewDTO.getProcessor_manufacturer(),  objectNewDTO.getProcessor_model(),  objectNewDTO.getDescription(),  objectNewDTO.isProcessor_itWorks() != null,  objectNewDTO.getProcessor_processorName(),  objectNewDTO.getProcessor_architecture(), null));
		
		// One to many relationships
		if(objectNewDTO.getRamMemory1_id() != null || objectNewDTO.getRamMemory1_manufacturer() != null || objectNewDTO.getRamMemory1_model() != null || objectNewDTO.getRamMemory1_description() != null || objectNewDTO.getRamMemory1_itWorks() != null || objectNewDTO.getRamMemory1_sizeInGB() != null || objectNewDTO.getRamMemory1_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory1_id(), null, null, objectNewDTO.getRamMemory1_manufacturer(), objectNewDTO.getRamMemory1_model(), objectNewDTO.getRamMemory1_description(), objectNewDTO.getRamMemory1_itWorks(), objectNewDTO.getRamMemory1_sizeInGB(), objectNewDTO.getRamMemory1_architecture(), null));
			
		if(objectNewDTO.getRamMemory2_id() != null || objectNewDTO.getRamMemory2_manufacturer() != null || objectNewDTO.getRamMemory2_model() != null || objectNewDTO.getRamMemory2_description() != null || objectNewDTO.getRamMemory2_itWorks() != null || objectNewDTO.getRamMemory2_sizeInGB() != null || objectNewDTO.getRamMemory2_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory2_id(), null, null, objectNewDTO.getRamMemory2_manufacturer(), objectNewDTO.getRamMemory2_model(), objectNewDTO.getRamMemory2_description(), objectNewDTO.getRamMemory2_itWorks(), objectNewDTO.getRamMemory2_sizeInGB(), objectNewDTO.getRamMemory2_architecture(), null));

		if(objectNewDTO.getRamMemory3_id() != null || objectNewDTO.getRamMemory3_manufacturer() != null || objectNewDTO.getRamMemory3_model() != null || objectNewDTO.getRamMemory3_description() != null || objectNewDTO.getRamMemory3_itWorks() != null || objectNewDTO.getRamMemory3_sizeInGB() != null || objectNewDTO.getRamMemory3_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory3_id(), null, null, objectNewDTO.getRamMemory3_manufacturer(), objectNewDTO.getRamMemory3_model(), objectNewDTO.getRamMemory3_description(), objectNewDTO.getRamMemory3_itWorks(), objectNewDTO.getRamMemory3_sizeInGB(), objectNewDTO.getRamMemory3_architecture(), null));

		if(objectNewDTO.getRamMemory4_id() != null || objectNewDTO.getRamMemory4_manufacturer() != null || objectNewDTO.getRamMemory4_model() != null || objectNewDTO.getRamMemory4_description() != null || objectNewDTO.getRamMemory4_itWorks() != null || objectNewDTO.getRamMemory4_sizeInGB() != null || objectNewDTO.getRamMemory4_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory4_id(), null, null, objectNewDTO.getRamMemory4_manufacturer(), objectNewDTO.getRamMemory4_model(), objectNewDTO.getRamMemory4_description(), objectNewDTO.getRamMemory4_itWorks(), objectNewDTO.getRamMemory4_sizeInGB(), objectNewDTO.getRamMemory4_architecture(), null));

		if(objectNewDTO.getRamMemory5_id() != null || objectNewDTO.getRamMemory5_manufacturer() != null || objectNewDTO.getRamMemory5_model() != null || objectNewDTO.getRamMemory5_description() != null || objectNewDTO.getRamMemory5_itWorks() != null || objectNewDTO.getRamMemory5_sizeInGB() != null || objectNewDTO.getRamMemory5_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory5_id(), null, null, objectNewDTO.getRamMemory5_manufacturer(), objectNewDTO.getRamMemory5_model(), objectNewDTO.getRamMemory5_description(), objectNewDTO.getRamMemory5_itWorks(), objectNewDTO.getRamMemory5_sizeInGB(), objectNewDTO.getRamMemory5_architecture(), null));
			
		if(objectNewDTO.getRamMemory6_id() != null || objectNewDTO.getRamMemory6_manufacturer() != null || objectNewDTO.getRamMemory6_model() != null || objectNewDTO.getRamMemory6_description() != null || objectNewDTO.getRamMemory6_itWorks() != null || objectNewDTO.getRamMemory6_sizeInGB() != null || objectNewDTO.getRamMemory6_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory6_id(), null, null, objectNewDTO.getRamMemory6_manufacturer(), objectNewDTO.getRamMemory6_model(), objectNewDTO.getRamMemory6_description(), objectNewDTO.getRamMemory6_itWorks(), objectNewDTO.getRamMemory6_sizeInGB(), objectNewDTO.getRamMemory6_architecture(), null));

		if(objectNewDTO.getRamMemory7_id() != null || objectNewDTO.getRamMemory7_manufacturer() != null || objectNewDTO.getRamMemory7_model() != null || objectNewDTO.getRamMemory7_description() != null || objectNewDTO.getRamMemory7_itWorks() != null || objectNewDTO.getRamMemory7_sizeInGB() != null || objectNewDTO.getRamMemory7_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory7_id(), null, null, objectNewDTO.getRamMemory7_manufacturer(), objectNewDTO.getRamMemory7_model(), objectNewDTO.getRamMemory7_description(), objectNewDTO.getRamMemory7_itWorks(), objectNewDTO.getRamMemory7_sizeInGB(), objectNewDTO.getRamMemory7_architecture(), null));

		if(objectNewDTO.getRamMemory8_id() != null || objectNewDTO.getRamMemory8_manufacturer() != null || objectNewDTO.getRamMemory8_model() != null || objectNewDTO.getRamMemory8_description() != null || objectNewDTO.getRamMemory8_itWorks() != null || objectNewDTO.getRamMemory8_sizeInGB() != null || objectNewDTO.getRamMemory8_architecture() != null)
			object.addRamMemory(new RamMemory(objectNewDTO.getRamMemory8_id(), null, null, objectNewDTO.getRamMemory8_manufacturer(), objectNewDTO.getRamMemory8_model(), objectNewDTO.getRamMemory8_description(), objectNewDTO.getRamMemory8_itWorks(), objectNewDTO.getRamMemory8_sizeInGB(), objectNewDTO.getRamMemory8_architecture(), null));

		if(objectNewDTO.getStorageDevice1_id() != null || objectNewDTO.getStorageDevice1_manufacturer() != null || objectNewDTO.getStorageDevice1_model() != null || objectNewDTO.getStorageDevice1_description() != null || objectNewDTO.getStorageDevice1_itWorks() != null || objectNewDTO.getStorageDevice1_sizeInGB() != null || objectNewDTO.getStorageDevice1_architecture() != null || objectNewDTO.getStorageDevice1_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice1_id(), null, null, objectNewDTO.getStorageDevice1_manufacturer(), objectNewDTO.getStorageDevice1_model(), objectNewDTO.getStorageDevice1_description(), objectNewDTO.getStorageDevice1_itWorks(), objectNewDTO.getStorageDevice1_sizeInGB(), objectNewDTO.getStorageDevice1_architecture(), objectNewDTO.getStorageDevice1_type(), null));

		if(objectNewDTO.getStorageDevice2_id() != null || objectNewDTO.getStorageDevice2_manufacturer() != null || objectNewDTO.getStorageDevice2_model() != null || objectNewDTO.getStorageDevice2_description() != null || objectNewDTO.getStorageDevice2_itWorks() != null || objectNewDTO.getStorageDevice2_sizeInGB() != null || objectNewDTO.getStorageDevice2_architecture() != null || objectNewDTO.getStorageDevice2_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice2_id(), null, null, objectNewDTO.getStorageDevice2_manufacturer(), objectNewDTO.getStorageDevice2_model(), objectNewDTO.getStorageDevice2_description(), objectNewDTO.getStorageDevice2_itWorks(), objectNewDTO.getStorageDevice2_sizeInGB(), objectNewDTO.getStorageDevice2_architecture(), objectNewDTO.getStorageDevice2_type(), null));

		if(objectNewDTO.getStorageDevice3_id() != null || objectNewDTO.getStorageDevice3_manufacturer() != null || objectNewDTO.getStorageDevice3_model() != null || objectNewDTO.getStorageDevice3_description() != null || objectNewDTO.getStorageDevice1_itWorks() != null || objectNewDTO.getStorageDevice3_sizeInGB() != null || objectNewDTO.getStorageDevice3_architecture() != null || objectNewDTO.getStorageDevice3_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice3_id(), null, null, objectNewDTO.getStorageDevice3_manufacturer(), objectNewDTO.getStorageDevice3_model(), objectNewDTO.getStorageDevice3_description(), objectNewDTO.getStorageDevice1_itWorks(), objectNewDTO.getStorageDevice3_sizeInGB(), objectNewDTO.getStorageDevice3_architecture(), objectNewDTO.getStorageDevice3_type(), null));

		if(objectNewDTO.getStorageDevice4_id() != null || objectNewDTO.getStorageDevice4_manufacturer() != null || objectNewDTO.getStorageDevice4_model() != null || objectNewDTO.getStorageDevice4_description() != null || objectNewDTO.getStorageDevice4_itWorks() != null || objectNewDTO.getStorageDevice4_sizeInGB() != null || objectNewDTO.getStorageDevice4_architecture() != null || objectNewDTO.getStorageDevice4_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice4_id(), null, null, objectNewDTO.getStorageDevice4_manufacturer(), objectNewDTO.getStorageDevice4_model(), objectNewDTO.getStorageDevice4_description(), objectNewDTO.getStorageDevice4_itWorks(), objectNewDTO.getStorageDevice4_sizeInGB(), objectNewDTO.getStorageDevice4_architecture(), objectNewDTO.getStorageDevice4_type(), null));

		if(objectNewDTO.getStorageDevice5_id() != null || objectNewDTO.getStorageDevice5_manufacturer() != null || objectNewDTO.getStorageDevice5_model() != null || objectNewDTO.getStorageDevice5_description() != null || objectNewDTO.getStorageDevice5_itWorks() != null || objectNewDTO.getStorageDevice5_sizeInGB() != null || objectNewDTO.getStorageDevice5_architecture() != null || objectNewDTO.getStorageDevice5_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice5_id(), null, null, objectNewDTO.getStorageDevice5_manufacturer(), objectNewDTO.getStorageDevice5_model(), objectNewDTO.getStorageDevice5_description(), objectNewDTO.getStorageDevice5_itWorks(), objectNewDTO.getStorageDevice5_sizeInGB(), objectNewDTO.getStorageDevice5_architecture(), objectNewDTO.getStorageDevice5_type(), null));

		if(objectNewDTO.getStorageDevice6_id() != null || objectNewDTO.getStorageDevice6_manufacturer() != null || objectNewDTO.getStorageDevice6_model() != null || objectNewDTO.getStorageDevice6_description() != null || objectNewDTO.getStorageDevice6_itWorks() != null || objectNewDTO.getStorageDevice6_sizeInGB() != null || objectNewDTO.getStorageDevice6_architecture() != null || objectNewDTO.getStorageDevice6_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice6_id(), null, null, objectNewDTO.getStorageDevice6_manufacturer(), objectNewDTO.getStorageDevice6_model(), objectNewDTO.getStorageDevice6_description(), objectNewDTO.getStorageDevice6_itWorks(), objectNewDTO.getStorageDevice6_sizeInGB(), objectNewDTO.getStorageDevice6_architecture(), objectNewDTO.getStorageDevice6_type(), null));

		if(objectNewDTO.getStorageDevice7_id() != null || objectNewDTO.getStorageDevice7_manufacturer() != null || objectNewDTO.getStorageDevice7_model() != null || objectNewDTO.getStorageDevice7_description() != null || objectNewDTO.getStorageDevice7_itWorks() != null || objectNewDTO.getStorageDevice7_sizeInGB() != null || objectNewDTO.getStorageDevice7_architecture() != null || objectNewDTO.getStorageDevice7_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice7_id(), null, null, objectNewDTO.getStorageDevice7_manufacturer(), objectNewDTO.getStorageDevice7_model(), objectNewDTO.getStorageDevice7_description(), objectNewDTO.getStorageDevice7_itWorks(), objectNewDTO.getStorageDevice7_sizeInGB(), objectNewDTO.getStorageDevice7_architecture(), objectNewDTO.getStorageDevice7_type(), null));

		if(objectNewDTO.getStorageDevice8_id() != null || objectNewDTO.getStorageDevice8_manufacturer() != null || objectNewDTO.getStorageDevice8_model() != null || objectNewDTO.getStorageDevice8_description() != null || objectNewDTO.getStorageDevice8_itWorks() != null || objectNewDTO.getStorageDevice8_sizeInGB() != null || objectNewDTO.getStorageDevice8_architecture() != null || objectNewDTO.getStorageDevice8_type() != null)
			object.addStorageDevice(new StorageDevice(objectNewDTO.getStorageDevice8_id(), null, null, objectNewDTO.getStorageDevice8_manufacturer(), objectNewDTO.getStorageDevice8_model(), objectNewDTO.getStorageDevice8_description(), objectNewDTO.getStorageDevice8_itWorks(), objectNewDTO.getStorageDevice8_sizeInGB(), objectNewDTO.getStorageDevice8_architecture(), objectNewDTO.getStorageDevice8_type(), null));
		
		// Many to many relationships
		if(objectNewDTO.getComputerUsersId() != null) {
			if(!objectNewDTO.getComputerUsersId().isEmpty()) {
				for(Long computerUserId : objectNewDTO.getComputerUsersId()) {
					ComputerUser computerUser = this.computerUserService.findById(computerUserId);
					computerUser.addUseTheComputer(object);
					object.addComputerUser(computerUser);
				}
			}
		}

		return object;
	}	
	
	
	
}

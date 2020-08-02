/** 
 * This is the class "MonitorService". Which will be able to intermediate the repository class and the service class.
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.domain.Monitor;
import com.pml.dto.MonitorNewDTO;
import com.pml.repositories.MonitorRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.IllegalArgException;
import com.pml.services.exceptions.InvalidQueryException;
import com.pml.services.exceptions.ObjectNotFoundException;
import com.pml.util.ServiceUtil;

@Service
public class MonitorService extends EquipmentService {
	@Autowired
	private MonitorRepository repository;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private ComputerService computerService;
	
	// List search methods
	public List<Monitor> findAll() {
		return this.repository.findByOrderByPatrimonyId();
	}
	
	public List<Monitor> findAllWithoutComputer() {
		return this.repository.findAllByComputerNull();
	}
	
	public Page<Monitor> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		try {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, Monitor.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + Monitor.class.getName() + "' class.");
        	return this.repository.findPageByOrderByPatrimonyId(pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
	}

	// Simple search methods	
	@Override
	public Monitor findById(Long id) {
		Optional<Monitor> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Monitor not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
		
	public Page<Monitor> search(Integer page, Integer linesPerPage, String direction, String orderBy, String searchTerm) {	
    	try {
    		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
    		        	
        	if(!ServiceUtil.parameterExistsInTheClass(orderBy, Monitor.class)) 
        		throw new InvalidQueryException("The value of orderBy parameter: '" + orderBy + "' doesn't exists in the '" + Monitor.class.getName() + "' class.");
        	return repository.search(searchTerm.toLowerCase(), pageRequest);
            
    	}
    	catch (IllegalArgumentException e) {
    		throw new IllegalArgException("The value of direction parameter: '" + direction + "' is invalid, this value must be 'ASC' or 'DESC'.");
		}
    }

	// Create, update and delete methods
	protected Monitor save(Monitor object) {
		if(object.getId() == null)
			return this.insert(object);
		return this.update(object);
	}
	
	@Transactional
	public Monitor insert(Monitor object) {
		if(object.getPatrimonyId() != null) {
			if(object.getPatrimonyId().equals(""))
				object.setPatrimonyId(null);
			else {
				if(this.alreadyExistsWithPatrimonyId(object))
					throw new ConflictOfObjectsException("This equipment already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
			}
		}
		object.setId(null);
		object.setCreatedDate(new Date());
		object.setLastModifiedDate(new Date());
		return this.repository.save(object);
	}

	public void delete(Long id) {
		this.findById(id);
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the monitor: id: '" + id + "'. This monitor has still dependents.");
		}
	}

	public Monitor update(Monitor object) {
		this.retrievesAndUpdatesDateData(object);	
		
		if(object.getPatrimonyId() != null) {
			if(object.getPatrimonyId().equals(""))
				object.setPatrimonyId(null);
			else {
				if(this.isPatrimonyIdChanged(object)){
					if(this.alreadyExistsWithPatrimonyId(object))
						throw new ConflictOfObjectsException("This equipment already exists: patrimonyId: '" + object.getPatrimonyId() + "'.");
				}
			}
		}

		updateReferences(object);
		return this.repository.saveAndFlush(object);
	}
	
	// Auxiliary methods
	protected Monitor findMonitorById(Long id) {
		Optional<Monitor> object = this.repository.findById(id);
		return object.get();
	}
	
	/**
	 * Performs a series of checks to make changes to the computer object.
	 * @param monitor
	 * @return void
	 */
	private void updateReferences(Monitor monitor) {
		// Get the previous monitor values
		Monitor oldMonitor = this.findById(monitor.getId());
		
		Computer oldComputer = oldMonitor.getComputer();		
		Computer currentComputer = monitor.getComputer();
		
		if (currentComputer != null) {
			// All computer are filled
			if (oldComputer != null) {
				if (currentComputer.equals(oldComputer))
					return;
				else {
					oldComputer.setMonitor(null);
					this.computerService.update(oldComputer);
					currentComputer.setMonitor(monitor);
					this.computerService.update(currentComputer);
				}
			}
			// Just current computer is filled
			else {
				currentComputer.setMonitor(monitor);
				this.computerService.update(currentComputer);
			}
		}	
		else {
			// Just old computer is filled
			if (oldComputer != null) {
				// Set old computer in null if the current computer is null
				oldComputer.setMonitor(null);
				this.computerService.update(oldComputer);
			}
		}		
	}
		
	/**
	 * Convert the MonitorNewDTO object to a Monitor object. 
	 * @param objectNewDTO MonitorNewDTO
	 * @return Monitor
	 */
	public Monitor fromDTO(MonitorNewDTO objectNewDTO) {
		Monitor object = new Monitor(
				null, objectNewDTO.getPatrimonyId(), null, null, null, objectNewDTO.getManufacturer(), 
				objectNewDTO.getModel(), objectNewDTO.getDescription(), null, null);
		
		if(objectNewDTO.getSectorId() != null)
			object.setSector(this.sectorService.findById(objectNewDTO.getSectorId()));
		if (objectNewDTO.getComputerId() != null)
			object.setComputer(this.computerService.findById(objectNewDTO.getComputerId()));
		return object;
	}
	
	
	
}

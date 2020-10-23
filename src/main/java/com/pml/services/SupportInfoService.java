/** 
 * This is the class "ComputerService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pml.domain.SupportInfo;
import com.pml.dto.ComputerNewDTO;
import com.pml.repositories.SupportInfoRepository;
import com.pml.services.exceptions.ConflictOfObjectsException;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class SupportInfoService {
	@Autowired
	private SupportInfoRepository repository;
	@Autowired
	private ComputerUserService computerService;
	@Autowired
	private ComputerUserService computerUserService;

	// List search methods
	public List<SupportInfo> findAll() {
		return this.repository.findAll();
	}
	
	public SupportInfo findById(Long id) {
		return this.repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("SupportInfo not found: id: '" + id + "'. Type: " + SupportInfo.class.getSimpleName()));
	}

	// Create, update and delete methods
	protected SupportInfo save(SupportInfo object) {
		if(object.getId() == null)
			return this.insert(object);
		return this.update(object);
	}
	
	@Transactional
	public SupportInfo insert(SupportInfo object) {
		if(this.alreadyExistsWithThisUser(object))
			throw new ConflictOfObjectsException("This support info already exists: user: '" + object.toString() + "'.");
		object.setId(null);
			
		this.manageReferences(object);
		
		return object;		
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
	public SupportInfo update(SupportInfo object) {
		if(this.alreadyExistsWithThisUser(object))
			throw new ConflictOfObjectsException("This equipment already exists: patrimonyId: '" + object.toString() + "'.");
		
		this.manageReferences(object);
		
		return object;
	}

	// Auxiliary methods
	// TODO Missing implementation
	/**
	 * Manages the Processor, RamMemories and StorageDevices references for the computer object in question.	
	 * @param object Computer
	 * @return void
	 */
	private boolean alreadyExistsWithThisUser(SupportInfo object) {
		return true;
	}
	
	// TODO Missing implementation
	private void manageReferences(SupportInfo object) {
		this.unlinkOldReferences(object);		
	}
	
	// TODO Missing implementation
	/**
	 * Unlink all ram memories and storage devices previously referring to the computer.  
	 * @param object Computer
	 * @return void
	 */
	private void unlinkOldReferences(SupportInfo object) {
	}
	
	// TODO Missing implementation
	/**
	 * Converts the SupportInfoNewDTO object to a SupportInfo object, populating all attributes to this respective SupportInfo. 
	 * @param objectDTO SupportInfoDTO
	 * @return SupportInfo
	 */
	public SupportInfo fromDTO(ComputerNewDTO objectNewDTO) {
		// Setting all attributes
		SupportInfo object = new SupportInfo();
		
		/*
		if(objectNewDTO.getProcessor_id() != null || objectNewDTO.getProcessor_manufacturer() != null || objectNewDTO.getProcessor_model() != null || objectNewDTO.getProcessor_description() != null || objectNewDTO.getProcessor_processorName() != null || objectNewDTO.getProcessor_architecture() != null) {
			if(objectNewDTO.getProcessor_id() != null) {				
				Processor processor = this.processorService.findById(objectNewDTO.getProcessor_id());
				Computer computer = this.findByProcessor(processor);
				if(computer != null) {
					computer.setProcessor(null);
					this.save(computer);
				}
				object.setProcessor(processor);
			}
			else {
				object.setProcessor(new Processor(objectNewDTO.getProcessor_id(), null, null, null, objectNewDTO.getProcessor_manufacturer(),  objectNewDTO.getProcessor_model(),  objectNewDTO.getDescription(),  objectNewDTO.getProcessor_processorName(),  objectNewDTO.getProcessor_architecture(), null));
			}
		}
		*/

		return object;
	}	
	
	
	
}

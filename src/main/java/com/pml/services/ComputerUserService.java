/** 
 * This is the class "ComputerUserService". Which will be able to intermediate the repository class and the service class.
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
import org.springframework.stereotype.Service;

import com.pml.domain.ComputerUser;
import com.pml.dto.ComputerUserDTO;
import com.pml.dto.ComputerUserNewDTO;
import com.pml.repositories.ComputerRepository;
import com.pml.repositories.ComputerUserRepository;
import com.pml.services.exceptions.DataIntegrityException;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class ComputerUserService {
	@Autowired
	private ComputerUserRepository repository;
	@Autowired
	private ComputerRepository computerRepository;
	
	public List<ComputerUser> findAll() {
		return this.repository.findAll();
	}
	
	public Page<ComputerUser> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		return this.repository.findAll(pageRequest);
	}
	
	public ComputerUser findById(Long id) {
		Optional<ComputerUser> object = this.repository.findById(id);
		return object.orElseThrow(()-> new ObjectNotFoundException("Computer user not found: id: '" + id + "'. Type: " + object.getClass().getName()));
	}
	
	@Transactional
	public ComputerUser insert(ComputerUser object) {
		object.setId(null);
		return this.repository.save(object);
	}

	public void delete(Long id) {
		ComputerUser objetc = this.findById(id);
		try {
			this.repository.deleteById(objetc.getId());
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Could not delete the computer user: id: '" + id + "'. This user has still dependents.");
		}
	}

	public ComputerUser update(ComputerUser object) {
		return this.repository.saveAndFlush(object);		
	}
	
	public ComputerUser fromDTO(ComputerUserDTO computerUserDTO) {
		ComputerUser computerUser = new ComputerUser(
				computerUserDTO.getId(), computerUserDTO.getName(), computerUserDTO.getLastName(),
				computerUserDTO.getSector().getCod(), computerUserDTO.getEmail());
		return computerUser;
	}
	
	public ComputerUser fromDTO(ComputerUserNewDTO computerUserNewDTO) {
		ComputerUser computerUser = new ComputerUser(
				null, computerUserNewDTO.getName(), computerUserNewDTO.getLastName(),
				computerUserNewDTO.getSector().getCod(), computerUserNewDTO.getEmail());
		for(Long computerId : computerUserNewDTO.getUseTheComputersId()) {
			computerUser.addUseTheComputer(this.computerRepository.getOne(computerId));
		}
		return computerUser;
	}
	
	
}

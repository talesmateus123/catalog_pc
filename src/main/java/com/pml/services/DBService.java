/** 
 * This is the class "DBService". Which will be able to instantiate all data domain and send it to database through their respective repositories.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import org.springframework.stereotype.Service;

import com.pml.repositories.ComputerRepository;
import com.pml.repositories.ComputerUserRepository;
import com.pml.repositories.MonitorRepository;

@Service
public class DBService {
	private ComputerRepository computerRepository;
	private ComputerUserRepository computerUserRepository;
	private MonitorRepository monitorRepository;
	
	public void instantiateTestDatabase() {
		
	}
}

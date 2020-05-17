/** 
 * This is the class "ComputerRepository", extended by the interface "JpaRepository". Which will be to represent a computer repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Computer;
import com.pml.domain.Monitor;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>{
	Optional<Computer> findByIpAddress(String ipAddress);
	Optional<Computer> findByMonitor(Monitor monitor);
	Optional<Computer> findByPatrimonyId(String patrimonyId);
	List<Computer> findByManufacturer(String manufacturer);	
	
	List<Computer> findAllByMonitorNull();
	
}

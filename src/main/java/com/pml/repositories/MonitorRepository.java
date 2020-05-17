/** 
 * This is the class "MonitorRepository", extended by the interface "JpaRepository". Which will be to represent a monitor repository.
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
public interface MonitorRepository extends JpaRepository<Monitor, Long>{
	Optional<Monitor> findByComputer(Computer computer);
	Optional<Monitor> findByPatrimonyId(String patrimonyId);
	
	List<Monitor> findAllByComputerNull();
	
}

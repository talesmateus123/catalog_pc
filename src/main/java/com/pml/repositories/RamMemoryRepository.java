/** 
 * This is the class "RamMemoryRepository", extended by the interface "JpaRepository". Which will be to represent a ram memory repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Computer;
import com.pml.domain.Monitor;
import com.pml.domain.RamMemory;
@Repository
public interface RamMemoryRepository extends JpaRepository<RamMemory, Long>{
	List<RamMemory> findByComputer(Computer computer);
	List<RamMemory> findAllByComputerNull();
	//List<Monitor> findByOrderByPatrimonyId();
	
	
	
}

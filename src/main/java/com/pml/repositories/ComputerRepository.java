/** 
 * This is the class "ComputerRepository", extended by the interface "JpaRepository". Which will be to represent a computer repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pml.domain.Computer;
import com.pml.domain.Monitor;
import com.pml.domain.Processor;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>{	
	List<Computer> findByOrderByPatrimonyId();
	Optional<Computer> findByMonitor(Monitor monitor);
	Optional<Computer> findByProcessor(Processor processor);
	List<Computer> findAllByMonitorNull();
	@Query("FROM Computer computer " +
	           "WHERE LOWER(computer.patrimonyId) like %:searchTerm% " +
	           "OR LOWER(computer.manufacturer) like %:searchTerm% " +
	           "OR LOWER(computer.model) like %:searchTerm% " +
	           "OR LOWER(computer.ipAddress) like %:searchTerm% " +
	           "OR LOWER(computer.hostName) like %:searchTerm% " +
	           "OR LOWER(computer.motherBoardName) like %:searchTerm% " +
	           "OR LOWER(computer.cabinetModel) like %:searchTerm% " +
	           "OR LOWER(computer.sector.name) like %:searchTerm%")
	Page<Computer> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
	
}

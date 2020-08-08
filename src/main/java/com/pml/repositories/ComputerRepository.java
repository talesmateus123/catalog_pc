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
	@Query("FROM Computer computer " +
	           "WHERE computer.deletedDate = null " +
				"ORDER BY computer.patrimonyId ASC")
	
	List<Computer> findByOrderByPatrimonyId();
	@Query("FROM Computer computer " +
	           "WHERE computer.deletedDate = null")
	Page<Computer> findPageByOrderByPatrimonyId(Pageable pageable);
	
	Optional<Computer> findByMonitor(Monitor monitor);
	
	List<Computer> findAllByMonitorNull();
	
	Optional<Computer> findByProcessor(Processor processor);
	
	// Generalized search
	@Query("SELECT computer FROM Computer computer " +
	           "WHERE computer.deletedDate = null " +
	           "AND (LOWER(computer.patrimonyId) like %:searchTerm% " +
	           "OR LOWER(computer.manufacturer) like %:searchTerm% " +
	           "OR LOWER(computer.model) like %:searchTerm% " +
	           "OR LOWER(computer.ipAddress) like %:searchTerm% " +
	           "OR LOWER(computer.hostName) like %:searchTerm% " +
	           "OR LOWER(computer.motherBoardName) like %:searchTerm% " +
	           "OR LOWER(computer.cabinetModel) like %:searchTerm% )" )
	Page<Computer> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	@Query("FROM Computer computer " +
	           "WHERE computer.deletedDate = null " +
	           "AND (LOWER(computer.processor.manufacturer) like %:searchTerm% " +
	           "OR LOWER(computer.processor.model) like %:searchTerm% " +
	           "OR LOWER(computer.processor.processorName) like %:searchTerm% )" )
	Page<Computer> searchByProcessorTerms(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	@Query("FROM Computer computer JOIN computer.computerUsers computerUser " +
				"WHERE computer.deletedDate = null " +
				"AND (LOWER(computerUser.name) like %:searchTerm% " +
				"OR LOWER(computerUser.lastName) like %:searchTerm% )" )
	Page<Computer> searchByComputerUserTerms(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	@Query("FROM Computer computer " +
			"WHERE computer.deletedDate = null AND computer.online = :searchTerm " )
	Page<Computer> searchByOnline(boolean searchTerm, Pageable pageable);
	
	@Query("FROM Computer computer " +
			"WHERE computer.deletedDate = null AND computer.onTheDomain = :searchTerm " )
	Page<Computer> searchByOnTheDomain(boolean searchTerm, Pageable pageable);
	
	@Query("FROM Computer computer " +
			"WHERE computer.deletedDate = null AND computer.personalComputer = :searchTerm " )
	Page<Computer> searchByPersonalComputer(boolean searchTerm, Pageable pageable);

	
	
}

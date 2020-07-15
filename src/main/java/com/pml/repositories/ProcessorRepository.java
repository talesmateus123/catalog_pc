/** 
 * This is the class "ProcessorRepository", extended by the interface "JpaRepository". Which will be to represent a processor repository.
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
import com.pml.domain.Processor;
@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long>{
	Optional<Processor> findByComputer(Computer computer);
	List<Processor> findAllByComputerNull();
	@Query("FROM Processor processor " +
	           "WHERE LOWER(processor.manufacturer) like %:searchTerm% " +
	           "OR LOWER(processor.model) like %:searchTerm% " +
	           "OR LOWER(processor.processorName) like %:searchTerm%")
	Page<Processor> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
	
}

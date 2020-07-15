/** 
 * This is the class "PrinterRepository", extended by the interface "JpaRepository". Which will be to represent a user repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pml.domain.Printer;
@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long>{
	Optional<Printer> findByPatrimonyId(String patrimonyId);
	@Query("FROM Printer printer " +
	           "WHERE LOWER(printer.patrimonyId) like %:searchTerm% " +
	           "OR LOWER(printer.manufacturer) like %:searchTerm% " +
	           "OR LOWER(printer.model) like %:searchTerm% " +
	           "OR LOWER(printer.ipAddress) like %:searchTerm% " +
	           "OR LOWER(printer.hostName) like %:searchTerm% " +
	           "OR LOWER(printer.sector.name) like %:searchTerm%")
	Page<Printer> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
	
}

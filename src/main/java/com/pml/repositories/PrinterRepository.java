/** 
 * This is the class "PrinterRepository", extended by the interface "JpaRepository". Which will be to represent a user repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Printer;
@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long>{
	Optional<Printer> findByPatrimonyId(String patrimonyId);
	
	
	
}

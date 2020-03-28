/** 
 * This is the class "ComputerRepository", extended by the interface "JpaRepository". Which will be to represent a computer repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.model.Computer;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>{
	// This method replaces the method "FindById", because it's don't applied to "String" type.
	Optional<Computer> findByPatrimonyId(String patrimonyId);
}

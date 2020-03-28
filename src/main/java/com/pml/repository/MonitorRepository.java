/** 
 * This is the class "MonitorRepository", extended by the interface "JpaRepository". Which will be to represent a monitor repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.model.Monitor;
@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long>{
	// This method replaces the method "FindById", because it's don't applied to "String" type.
	Optional<Monitor> findByPatrimonyId(String patrimonyId);	
}

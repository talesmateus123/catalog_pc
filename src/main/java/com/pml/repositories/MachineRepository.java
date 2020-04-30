/** 
 * This is the class "MachineRepository", extended by the interface "JpaRepository". Which will be to represent a computer repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Machine;
@Repository
public interface MachineRepository extends JpaRepository<Machine, Long>{
	Optional<Machine> findByPatrimonyId(String patrimonyId);
	
}

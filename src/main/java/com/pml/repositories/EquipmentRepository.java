/** 
 * This is the class "MachineRepository", extended by the interface "JpaRepository". Which will be to represent a computer repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Equipment;
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
	Optional<Equipment> findByPatrimonyId(String patrimonyId);
	List<Equipment> findByManufacturer(String manufacturer);
	
	
	
}

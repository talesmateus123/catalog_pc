/** 
 * This is the class "ProcessorRepository", extended by the interface "JpaRepository". Which will be to represent a processor repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Electronic;
@Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Long>{
	List<Electronic> findAllByItComposedFalse();
	
	
	
}

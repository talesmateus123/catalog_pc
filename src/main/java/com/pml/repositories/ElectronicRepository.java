/** 
 * This is the class "ProcessorRepository", extended by the interface "JpaRepository". Which will be to represent a processor repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pml.domain.Electronic;
@Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Long>{
	List<Electronic> findAllByItComposedFalse();
	@Query("FROM Electronic electronic " +
	           "WHERE equipmentType = :equipmentType AND LOWER(electronic.manufacturer) like %:searchTermManufacturer% " +
	           "OR LOWER(electronic.model) like %:searchTermModel%")
	Page<Electronic> search(@Param("searchTermManufacturer") String searchTermManufacturer, @Param("searchTermModel") String searchTermModel, @Param("equipmentType") String equipmentType, Pageable pageable);
	
	
}

/** 
 * This is the class "SectorRepository", extended by the interface "JpaRepository". Which will be to represent a computer repository.
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

import com.pml.domain.Sector;
@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer>{
	Optional<Sector> findByName(String name);
	Optional<Sector> findByPhone(String phone);
	List<Sector> findByOrderByName();
	@Query("FROM Sector sector " +
	           "WHERE LOWER(sector.name) like %:searchTerm% ")
	Page<Sector> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
	
}

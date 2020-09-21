/** 
 * This is the class "MonitorRepository", extended by the interface "JpaRepository". Which will be to represent a monitor repository.
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

import com.pml.domain.Monitor;
@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long>{
	@Query("FROM Monitor monitor " +
				"ORDER BY monitor.patrimonyId ASC")
	List<Monitor> findByOrderByPatrimonyId();
	
	@Query("FROM Monitor monitor ")
	Page<Monitor> findPageByOrderByPatrimonyId(Pageable pageable);

	@Query("FROM Monitor monitor " +
	           "WHERE LOWER(monitor.patrimonyId) like %:searchTerm% " +
	           "OR LOWER(monitor.manufacturer) like %:searchTerm% " +
	           "OR LOWER(monitor.model) like %:searchTerm% " +
				"OR LOWER(monitor.sector.name) like %:searchTerm%")
	Page<Monitor> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
	
}

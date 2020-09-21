/** 
 * This is the class "NetworkDeviceRepository", extended by the interface "JpaRepository". Which will be to represent a user repository.
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

import com.pml.domain.NetworkDevice;
@Repository
public interface NetworkDeviceRepository extends JpaRepository<NetworkDevice, Long>{
	@Query("FROM NetworkDevice networkDevice " +
				"ORDER BY networkDevice.patrimonyId ASC")
	List<NetworkDevice> findByOrderByPatrimonyId();
	
	@Query("FROM NetworkDevice networkDevice ")
	Page<NetworkDevice> findPageByOrderByPatrimonyId(Pageable pageable);
	
	@Query("FROM NetworkDevice networkDevice " +
	           "WHERE LOWER(networkDevice.patrimonyId) like %:searchTerm% " +
	           "OR LOWER(networkDevice.manufacturer) like %:searchTerm% " +
	           "OR LOWER(networkDevice.model) like %:searchTerm% " +
	           "OR LOWER(networkDevice.ipAddress) like %:searchTerm% " +
	           "OR LOWER(networkDevice.hostName) like %:searchTerm% " +
	           "OR LOWER(networkDevice.sector.name) like %:searchTerm%")
	Page<NetworkDevice> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
	
}

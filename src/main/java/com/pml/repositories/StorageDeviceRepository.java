/** 
 * This is the class "StorageDeviceRepository", extended by the interface "JpaRepository". Which will be to represent a storage device repository.
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

import com.pml.domain.Computer;
import com.pml.domain.StorageDevice;
@Repository
public interface StorageDeviceRepository extends JpaRepository<StorageDevice, Long>{
	List<StorageDevice> findByComputer(Computer computer);	
	List<StorageDevice> findAllByComputerNull();
	@Query("FROM StorageDevice storageDevice " +
	           "WHERE LOWER(storageDevice.manufacturer) like %:searchTerm% " +
	           "OR LOWER(storageDevice.model) like %:searchTerm% ")
	Page<StorageDevice> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	
	
}

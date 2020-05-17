/** 
 * This is the class "StorageDeviceRepository", extended by the interface "JpaRepository". Which will be to represent a storage device repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Computer;
import com.pml.domain.StorageDevice;
@Repository
public interface StorageDeviceRepository extends JpaRepository<StorageDevice, Long>{
	List<StorageDevice> findByComputer(Computer computer);	
	List<StorageDevice> findAllByComputerNull();
	
	
	
}

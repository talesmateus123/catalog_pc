/** 
 * This is the class "ComputerUserRepository", extended by the interface "JpaRepository". Which will be to represent a ComputerUser repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Computer;
import com.pml.domain.ComputerUser;
import com.pml.domain.Sector;
@Repository
public interface ComputerUserRepository extends JpaRepository<ComputerUser, Long>{
	Optional<ComputerUser> findByEmail(String email);
	List<ComputerUser> findByUseTheComputers(Computer computer);
	List<ComputerUser> findByName(String name);
	List<ComputerUser> findByLastName(String lastName);
	List<ComputerUser> findBySector(Sector sector);
	
	
	
}

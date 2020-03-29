/** 
 * This is the class "ComputerUserRepository", extended by the interface "JpaRepository". Which will be to represent a ComputerUser repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.models.ComputerUser;
@Repository
public interface ComputerUserRepository extends JpaRepository<ComputerUser, Long>{
	
}

/** 
 * This is the class "UserRepository", extended by the interface "JpaRepository". Which will be to represent a user repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	Optional<Client> findByEmail(String email);
	
}

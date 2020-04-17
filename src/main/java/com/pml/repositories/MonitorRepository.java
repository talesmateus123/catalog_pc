/** 
 * This is the class "MonitorRepository", extended by the interface "JpaRepository". Which will be to represent a monitor repository.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pml.domain.Monitor;
@Repository
public interface MonitorRepository extends JpaRepository<Monitor, String>{
}

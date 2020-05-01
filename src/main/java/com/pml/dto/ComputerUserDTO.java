/** 
 * This is the class "ComputerUserDTO". That class will be to represent a computer user dto.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;

import com.pml.domain.ComputerUser;

public class ComputerUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String lastName;
	private String email;
	
	public ComputerUserDTO() {		
	}
	
	public ComputerUserDTO(ComputerUser computerUser) {
		this.id = computerUser.getId(); 
		this.name = computerUser.getName();
		this.lastName = computerUser.getLastName();
		this.email = computerUser.getEmail();
	}
	
	public ComputerUserDTO(Long id, String name, String lastName, String email) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

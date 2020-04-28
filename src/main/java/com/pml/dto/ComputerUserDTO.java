/** 
 * This is the class "ComputerUserDTO". That class will be to represent a computer user dto.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;

import com.pml.domain.ComputerUser;
import com.pml.domain.enums.Sector;

public class ComputerUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String lastName;
	private Integer sector;
	private String email;
	
	public ComputerUserDTO() {		
	}
	
	public ComputerUserDTO(ComputerUser computerUser) {
		this.id = computerUser.getId(); 
		this.name = computerUser.getName();
		this.lastName = computerUser.getLastName();
		this.sector = computerUser.getSector().getCod();
		this.email = computerUser.getEmail();
	}
	
	public ComputerUserDTO(Long id, String name, String lastName, Integer sector, String email) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.sector = sector;
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
	
	public Sector getSector() {
		return Sector.toEnum(sector);
	}
	
	public void setSector(Sector location) {
		this.sector = location.getCod();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

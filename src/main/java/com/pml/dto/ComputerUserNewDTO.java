/** 
 * This is the class "ComputerUserDTO". That class will be to represent a computer user dto.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.Sector;

public class ComputerUserNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 20, message = "The text of this field must contain between 4 and 20 characters")
	private String name;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 20, message = "The text of this field must contain between 4 and 20 characters")
	private String lastName;
	@NotNull(message = "This field is mandatory")
	private Integer sector;
	@Email(message = "Invalid e-mail")
	private String email;
	private List<Long> useTheComputersId = new ArrayList<>();
	
	public ComputerUserNewDTO() {		
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
	
	public List<Long> getUseTheComputersId() {
		return useTheComputersId;
	}

	public void setUseTheComputersId(List<Long> useTheComputersId) {
		this.useTheComputersId = useTheComputersId;
	}
	
	/**
	 * Add a new computer id Long to this list of computers usage.
	 * @param useTheComputerId Long
	 * @return void
	 */
	public void addUseTheComputer(Long useTheComputerId) {
		this.useTheComputersId.add(useTheComputerId);
	}


}

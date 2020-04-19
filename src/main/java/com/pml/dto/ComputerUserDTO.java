/** 
 * This is the class "ComputerUser". That class will be to represent a computer user.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.Sector;

@Entity
public class ComputerUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 20, message = "The text of this field must contain between 4 and 20 characters")
	private String name;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 20, message = "The text of this field must contain between 4 and 20 characters")
	private String lastName;
	@NotNull
	private Integer sector;
	@Email(message = "Invalid e-mail")
	private String email;
	
	public ComputerUserDTO() {		
	}
	
	public ComputerUserDTO(Long id, String name, String lastName, Integer sector, String email) {
		super();
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerUserDTO other = (ComputerUserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

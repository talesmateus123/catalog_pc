/** 
 * This is the class "ComputerUser". That class will be to represent a computer user.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pml.domain.enums.Sector;

@Entity
public class ComputerUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String lastName;
	private Integer sector;
	private String email;
	// A user can use many computer, because the company uses the Active Directory server.
	@JsonBackReference
	@ManyToMany(mappedBy = "computerUsers")
	private List<Computer> useTheComputers = new ArrayList<>();
	
	public ComputerUser() {		
	}
	
	public ComputerUser(Long id, String name, String lastName, Integer sector, String email) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.sector = (sector == null) ? null : sector;
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

	public List<Computer> getUseTheComputers() {
		return useTheComputers;
	}

	public void setUseTheComputers(List<Computer> useTheComputers) {
		this.useTheComputers = useTheComputers;
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
		ComputerUser other = (ComputerUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

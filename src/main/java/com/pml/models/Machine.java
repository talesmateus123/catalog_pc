/** 
 * This is the abstract class "Machine", which it will be extended by the class "Computer" and class "Monitor".
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public abstract class Machine implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Size(max = 10)
	private String patrimonyId;
	@NotEmpty
	@Size(max = 20)
	private String machineType;
	@Size(max = 100)
	private String manufacturer;
	@NotEmpty
	@Size(max = 100)
	private String model;
	private String description;
	private String location = "N/A";
	
	public String getPatrimonyId() {
		return patrimonyId;
	}
	public void setPatrimonyId(String patrimonyId) {
		this.patrimonyId = patrimonyId;
	}	
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patrimonyId == null) ? 0 : patrimonyId.hashCode());
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
		Machine other = (Machine) obj;
		if (patrimonyId == null) {
			if (other.patrimonyId != null)
				return false;
		} else if (!patrimonyId.equals(other.patrimonyId))
			return false;
		return true;
	}
	
	
}

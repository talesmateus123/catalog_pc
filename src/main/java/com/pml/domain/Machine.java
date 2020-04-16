/** 
 * This is the abstract class "Machine", which it will be extended by the class "Computer" and class "Monitor".
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.enums.MachineType;
import com.pml.domain.enums.Sector;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Machine implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Size(max = 10)
	private String patrimonyId;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date modifiedDate;
	@javax.validation.constraints.NotNull
	private Integer machineType;
	@Size(max = 100)
	private String manufacturer;
	@NotEmpty
	@Size(max = 100)
	private String model;
	private String description;
	@javax.validation.constraints.NotNull
	private Integer sector;
	
	public Machine() {
	}
	
	public Machine(@Size(max = 10) String patrimonyId, Date createdDate, Date modifiedDate, @NotEmpty MachineType machineType,
			@Size(max = 100) String manufacturer, @NotEmpty @Size(max = 100) String model, String description,
			Integer sector) {
		super();
		this.patrimonyId = patrimonyId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.machineType = machineType.getCod();
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.sector = sector;
	}
	
	public String getPatrimonyId() {
		return patrimonyId;
	}
	
	public void setPatrimonyId(String patrimonyId) {
		this.patrimonyId = patrimonyId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public MachineType getMachineType() {
		return MachineType.toEnum(machineType);
	}
	
	public void setMachineType(MachineType machineType) {
		this.machineType = machineType.getCod();
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
	
	public Sector getSector() {
		return Sector.toEnum(sector);
	}
	
	public void setSector(Sector location) {
		this.sector = location.getCod();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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

/** 
 * This is the abstract class "Equipment", which it will be extended by the class "Computer" and class "Monitor".
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.enums.EquipmentType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date modifiedDate;
	private Integer equipmentType;
	private String manufacturer;
	private String model;
	private String description;
	private boolean isItWorking = true;
	// Equipment composed is not a computer part 
	private boolean isItComposed;

	
	public Equipment() {
	}
	
	public Equipment(Date createdDate, Date modifiedDate, EquipmentType equipmentType, 
			String manufacturer, String model, String description, boolean isItWorking, boolean isItComposed) {
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.equipmentType = equipmentType.getCod();
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.isItWorking = isItWorking;
		this.isItComposed = isItComposed;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public EquipmentType getEquipmentType() {
		return EquipmentType.toEnum(equipmentType);
	}
	
	public void setEquipmentType(EquipmentType equipmentType) {
		this.equipmentType = equipmentType.getCod();
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
	
	public boolean isItWorking() {
		return isItWorking;
	}

	public void setIsItWorking(boolean isItWorking) {
		this.isItWorking = isItWorking;
	}

	public boolean isItComposed() {
		return isItComposed;
	}

	public void setItComposed(boolean isItComposed) {
		this.isItComposed = isItComposed;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Equipment other = (Equipment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

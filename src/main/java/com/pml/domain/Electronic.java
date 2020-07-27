/** 
 * This is the abstract class "Electronic", which it will be able to represent a any electronic * 
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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.enums.EquipmentType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Electronic implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	protected Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	protected Date lastModifiedDate;
	protected Integer equipmentType;
	protected String manufacturer = "Undefined";
	protected String model = "";
	@Size(max = 100, message = "The text must contain a maximum of 100 characters")
	protected String description = "";
	protected Boolean itWorks = true;
	protected Boolean itComposed;
	
	public Electronic() {
	}
	
	public Electronic(Long id, Date createdDate, Date lastModifiedDate, EquipmentType equipmentType, 
			String manufacturer, String model, String description, Boolean itWorks, Boolean itComposed) {
		this.id = id;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.equipmentType = (equipmentType == null) ? null : equipmentType.getCod();		
		this.manufacturer = manufacturer ;
		this.model = model;
		this.description = description;		
		this.itWorks = itWorks;
		this.itComposed = itComposed;
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

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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
	
	public Boolean isItWorks() {
		return itWorks;
	}

	public void setItWorks(Boolean itWorks) {
		this.itWorks = itWorks;
	}

	public Boolean isItComposed() {
		return itComposed;
	}

	public void setItComposed(Boolean isItComposed) {
		this.itComposed = isItComposed;
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
		Electronic other = (Electronic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Electronic [id=");
		builder.append(id);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", lastModifiedDate=");
		builder.append(lastModifiedDate);
		builder.append(", equipmentType=");
		builder.append(equipmentType);
		builder.append(", manufacturer=");
		builder.append(manufacturer);
		builder.append(", model=");
		builder.append(model);
		builder.append(", description=");
		builder.append(description);
		builder.append(", itWorks=");
		builder.append(itWorks);
		builder.append(", itComposed=");
		builder.append(itComposed);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}

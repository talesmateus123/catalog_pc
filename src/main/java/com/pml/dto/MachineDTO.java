package com.pml.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.Sector;

public abstract class MachineDTO {
	private static final long serialVersionUID = 1L;
	private Long id;
	@Size(min = 4, max = 10, message = "The text must contain between 4 and 10 characters")
	private String patrimonyId;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date lastModifiedDate;
	private Integer equipmentType;
	@NotEmpty(message = "Mandatory")
	private String manufacturer;
	@NotEmpty(message = "Mandatory")
	private String model;
	@Size(max = 100, message = "The text must contain a maximum of 100 characters")
	private String description;
	private Integer sector;
	private boolean itWorks;
	private boolean itComposed;
	
	public MachineDTO() {	
		this.setItComposed(true);
	}
		
	public MachineDTO(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, Integer equipmentType,
			String manufacturer, String model, String description, Integer sector, boolean itWorks, boolean itComposed) {
		this.id = id;
		this.patrimonyId = patrimonyId;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.equipmentType = equipmentType;
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.sector = sector;
		this.itWorks = itWorks;
		this.itComposed = itComposed;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Sector getSector() {
		return Sector.toEnum(sector);
	}
	
	public void setSector(Sector location) {
		this.sector = location.getCod();
	}
	
	public boolean isItWorks() {
		return itWorks;
	}

	public void setItWorks(boolean isItWorks) {
		this.itWorks = isItWorks;
	}

	public boolean isItComposed() {
		return itComposed;
	}

	public void setItComposed(boolean itComposed) {
		this.itComposed = itComposed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

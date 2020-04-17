package com.pml.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.enums.MachineType;
import com.pml.domain.enums.Sector;

public abstract class MachineDTO {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty(message = "Mandatory")
	@Size(min = 4, max = 10, message = "The text must contain between 4 and 10 characters")
	private String patrimonyId;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date modifiedDate;
	private Integer machineType;
	private String manufacturer;
	private String model;
	private String description;
	private Integer sector;
	private boolean isItWorking;
	
	public MachineDTO() {		
	}
		
	public MachineDTO(Long id, String patrimonyId, Date createdDate, Date modifiedDate, Integer machineType,
			String manufacturer, String model, String description, Integer sector, boolean isItWorking) {
		this.id = id;
		this.patrimonyId = patrimonyId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.machineType = machineType;
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.sector = sector;
		this.setItWorking(isItWorking);
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
	
	public boolean isItWorking() {
		return isItWorking;
	}

	public void setItWorking(boolean isItWorking) {
		this.isItWorking = isItWorking;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.Monitor;
import com.pml.domain.enums.EquipmentType;

public class MonitorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String patrimonyId;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date lastModifiedDate;
	private Integer equipmentType;
	private String manufacturer;
	private String model;
	private String description;
	private boolean itWorks;
	private boolean itComposed;
	private String sectorName;
	
	public MonitorDTO() {
	}
	
	public MonitorDTO(Monitor monitor) {
		this.id = monitor.getId();
		this.patrimonyId = monitor.getPatrimonyId();
		this.createdDate = monitor.getCreatedDate();
		this.lastModifiedDate = monitor.getLastModifiedDate();
		this.equipmentType = monitor.getEquipmentType().getCod();
		this.manufacturer = monitor.getManufacturer();
		this.model = monitor.getModel();
		this.description = monitor.getDescription();
		this.itComposed = monitor.isItComposed();
		this.equipmentType = monitor.getEquipmentType().getCod();
		this.itComposed = monitor.isItComposed();
		this.sectorName = monitor.getSector().getName();
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
	
	public String getSectorName() {
		return sectorName;
	}
	
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	
	

}

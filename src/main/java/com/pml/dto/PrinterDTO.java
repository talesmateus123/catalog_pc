package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.Printer;
import com.pml.domain.enums.EquipmentType;

public class PrinterDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date lastModifiedDate;
	private Integer equipmentType;
	private String manufacturer;
	private String model;
	private String description;
	private boolean itWorks = true; 
	private boolean itComposed;
	private String patrimonyId = "";
	private String ipAddress;
	private String hostName;
	
	public PrinterDTO() {
	}
	
	public PrinterDTO(Printer printer) {
		this.id = printer.getId();
		this.createdDate = printer.getCreatedDate();
		this.lastModifiedDate = printer.getLastModifiedDate();
		this.equipmentType = printer.getEquipmentType().getCod();
		this.manufacturer = printer.getManufacturer();
		this.model = printer.getModel();
		this.description = printer.getDescription();
		this.itWorks = printer.isItWorks();
		this.itComposed = printer.isItComposed();
		this.patrimonyId = printer.getPatrimonyId();
		this.ipAddress = printer.getIpAddress();
		this.hostName = printer.getHostName();
		this.itComposed = printer.isItComposed();
	}
	
	public PrinterDTO(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, String manufacturer, 
			String model, String description, boolean itWorks, String ipAddress, String hostName,
			EquipmentType equipmentType, boolean itComposed) {
		this.id = id;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.equipmentType = equipmentType.getCod();
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.itWorks = itWorks;
		this.itComposed = itComposed;
		this.patrimonyId = patrimonyId;
		this.ipAddress = ipAddress;
		this.hostName = hostName;
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
	
	public boolean isItWorks() {
		return itWorks;
	}

	public void setItWorks(boolean itWorks) {
		this.itWorks = itWorks;
	}

	public boolean isItComposed() {
		return itComposed;
	}

	public void setItComposed(boolean itComposed) {
		this.itComposed = itComposed;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	

	
}

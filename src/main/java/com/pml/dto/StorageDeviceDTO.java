package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.StorageDevice;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.StorageDeviceArchitecture;
import com.pml.domain.enums.StorageDeviceType;

public class StorageDeviceDTO implements Serializable {
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
	private Integer sizeInMB;
	private Integer architecture;
	private Integer type;
	
	
	public StorageDeviceDTO() {
	}
	
	public StorageDeviceDTO(StorageDevice storageDevice) {
		this.id = storageDevice.getId();
		this.createdDate = storageDevice.getCreatedDate();
		this.lastModifiedDate = storageDevice.getLastModifiedDate();
		this.equipmentType = storageDevice.getEquipmentType().getCod();
		this.manufacturer = storageDevice.getManufacturer();
		this.model = storageDevice.getModel();
		this.description = storageDevice.getDescription();
		this.itWorks = storageDevice.isItWorks();
		this.itComposed = storageDevice.isItComposed();
		this.sizeInMB = storageDevice.getSizeInMB();
		this.architecture = storageDevice.getArchitecture().getCod();	
		this.type = storageDevice.getType().getCod();	
	}
	
	public StorageDeviceDTO(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate,  EquipmentType equipmentType,
			String manufacturer, String model, String description, Integer sector, boolean itWorks, boolean itComposed, 
			Integer sizeInMB, StorageDeviceArchitecture architecture, StorageDeviceType type) {
		this.id = id;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.equipmentType = equipmentType.getCod();
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.itWorks = itWorks;
		this.itComposed = itComposed;
		this.sizeInMB = sizeInMB;
		this.architecture = architecture.getCod();
		this.type = type.getCod();
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
	
	public StorageDeviceType getType() {
		return StorageDeviceType.toEnum(type);
	}
	
	public void setType(StorageDeviceType type) {
		this.type = type.getCod();
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

	public void setItComposed(boolean isItComposed) {
		this.itComposed = isItComposed;
	}

	public Integer getSizeInMB() {
		return sizeInMB;
	}

	public void setSizeInMB(Integer sizeInMB) {
		this.sizeInMB = sizeInMB;
	}
	
	public StorageDeviceArchitecture getArchitecture() {
		return StorageDeviceArchitecture.toEnum(architecture);
	}
	
	public void setArchitecture(StorageDeviceArchitecture architecture) {
		this.architecture = architecture.getCod();
	}

	
}

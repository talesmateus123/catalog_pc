package com.pml.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.StorageDeviceArchitecture;
import com.pml.domain.enums.StorageDeviceType;

@Entity
public class StorageDevice extends Electronic {
	private static final long serialVersionUID = 1L;
	@NotNull
	private Double sizeInMB;
	@NotNull
	private Integer architecture;
	@NotNull
	private Integer type;
	@ManyToOne
	@JoinColumn(name = "computer_id")
	@JsonBackReference
	private Computer computer;
	
	public StorageDevice() {
		super();
		this.setEquipmentType(EquipmentType.STORAGE_DEVICE);
		this.setItComposed(false);
	}
	
	public StorageDevice(Long id, Date createdDate, Date lastModifiedDate, String manufacturer,
			String model, String description, boolean itWorks, Double sizeInMB, 
			StorageDeviceArchitecture storageDeviceArchitecture, StorageDeviceType type, Computer computer) {
		super(id, createdDate, lastModifiedDate, EquipmentType.STORAGE_DEVICE, manufacturer, model, description, itWorks, false);
		this.sizeInMB = sizeInMB;
		this.architecture = storageDeviceArchitecture.getCod();
		this.type = type.getCod();
		this.computer = computer;
		this.setItComposed(false);
	}
		
	public Double getSizeInMB() {
		return sizeInMB;
	}
	
	public void setSizeInMB(Double sizeInMB) {
		this.sizeInMB = sizeInMB;
	}
	
	public StorageDeviceArchitecture getArchitecture() {
		return StorageDeviceArchitecture.toEnum(architecture);
	}
	
	public void setArchitecture(StorageDeviceArchitecture architecture) {
		this.architecture = architecture.getCod();
	}
	
	public StorageDeviceType getType() {
		return StorageDeviceType.toEnum(type);
	}
	
	public void setType(StorageDeviceType type) {
		this.type = type.getCod();
	}
	
	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	
}

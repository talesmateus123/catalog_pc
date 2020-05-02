package com.pml.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.StorageDeviceArchitecture;
import com.pml.domain.enums.StorageDeviceType;

public class StorageDeviceNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String manufacturer;
	private String model;
	@Size(max = 100, message = "The text must contain a maximum of 100 characters")
	private String description;
	private boolean itWorks = true;
	@NotNull(message = "This field is mandatory")
	private Integer sizeInMB;
	@NotNull(message = "This field is mandatory")
	private Integer architecture;
	@NotNull(message = "This field is mandatory")
	private Integer type;
	private Long computerId;
	
	public StorageDeviceNewDTO() {
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
	
	public StorageDeviceType getType() {
		return StorageDeviceType.toEnum(type);
	}
	
	public void setType(StorageDeviceType type) {
		this.type = type.getCod();
	}
	
	
	public Long getComputerId() {
		return computerId;
	}

	public void setComputerId(Long computerId) {
		this.computerId = computerId;
	}

	
	
}

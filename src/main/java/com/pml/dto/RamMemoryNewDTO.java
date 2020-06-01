package com.pml.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.RamMemoryArchitecture;

public class RamMemoryNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String manufacturer;
	private String model;
	@Size(max = 100, message = "The text must contain a maximum of 100 characters")
	private String description;
	private boolean itWorks = true;
	@NotNull(message = "This field is mandatory")
	private Double sizeInGB;
	@NotNull(message = "This field is mandatory")
	private Integer architecture;
	private Long computerId;
	
	public RamMemoryNewDTO() {
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
	
	public Double getSizeInGB() {
		return sizeInGB;
	}

	public void setSizeInGB(Double sizeInGB) {
		this.sizeInGB = sizeInGB;
	}
	
	public RamMemoryArchitecture getArchitecture() {
		return RamMemoryArchitecture.toEnum(architecture);
	}

	public void setArchitecture(RamMemoryArchitecture architecture) {
		this.architecture = architecture.getCod();
	}
	
	public Long getComputerId() {
		return computerId;
	}

	public void setComputerId(Long computerId) {
		this.computerId = computerId;
	}
	

	
}

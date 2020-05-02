package com.pml.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.ArchitectureType;

public class ProcessorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String manufacturer;
	private String model;
	@Size(max = 100, message = "The text must contain a maximum of 100 characters")
	private String description;
	private boolean itWorks = true;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 30, message = "The text of this field must contain between 4 and 10 characters")
	private String processorName;
	@NotNull(message = "This field is mandatory")
	private Integer architecture;
	private Long computerId;
	
	public ProcessorNewDTO() {
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
	
	public String getProcessorName() {
		return processorName;
	}

	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

	public ArchitectureType getArchitecture() {
		return ArchitectureType.toEnum(architecture);
	}
	
	public void setArchitecture(ArchitectureType architecture) {
		this.architecture = architecture.getCod();
	}
	
	public Long getComputerId() {
		return computerId;
	}

	public void setComputerId(Long computerId) {
		this.computerId = computerId;
	}
	
	
	
}

package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.Processor;
import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.EquipmentType;

public class ProcessorDTO implements Serializable {
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
	private String processorName;
	private Integer architecture;
	
	public ProcessorDTO() {
	}
	
	public ProcessorDTO(Processor processor) {
		this.id = processor.getId();
		this.createdDate = processor.getCreatedDate();
		this.lastModifiedDate = processor.getLastModifiedDate();
		this.equipmentType = processor.getEquipmentType().getCod();
		this.manufacturer = processor.getManufacturer();
		this.model = processor.getModel();
		this.description = processor.getDescription();
		this.itWorks = processor.isItWorks();
		this.itComposed = processor.isItComposed();
		this.processorName = processor.getProcessorName();
		this.architecture = processor.getArchitecture().getCod();	
	}
	
	public ProcessorDTO(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate,  EquipmentType equipmentType,
			String manufacturer, String model, String description, Integer sector, boolean itWorks, boolean itComposed, 
			String processorName, Integer architecture) {
		this.id = id;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.equipmentType = equipmentType.getCod();
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.itWorks = itWorks;
		this.itComposed = itComposed;
		this.processorName = processorName;
		this.architecture = architecture;
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
	
	
}

package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.RamMemory;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.RamMemoryArchitecture;

public class RamMemoryDTO implements Serializable {
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
	private boolean itWorks; 
	private boolean itComposed;
	private Integer sizeInMB;
	private Integer architecture;
	
	
	public RamMemoryDTO() {
	}
	
	public RamMemoryDTO(RamMemory ramMemory) {
		this.id = ramMemory.getId();
		this.createdDate = ramMemory.getCreatedDate();
		this.lastModifiedDate = ramMemory.getLastModifiedDate();
		this.equipmentType = ramMemory.getEquipmentType().getCod();
		this.manufacturer = ramMemory.getManufacturer();
		this.model = ramMemory.getModel();
		this.description = ramMemory.getDescription();
		this.itWorks = ramMemory.isItWorks();
		this.itComposed = ramMemory.isItComposed();
		this.sizeInMB = ramMemory.getSizeInMB();
		this.architecture = ramMemory.getArchitecture().getCod();		
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

	public Integer getSizeInMB() {
		return sizeInMB;
	}

	public void setSizeInMB(Integer sizeInMB) {
		this.sizeInMB = sizeInMB;
	}
	
	public RamMemoryArchitecture getArchitecture() {
		return RamMemoryArchitecture.toEnum(architecture);
	}
	
	public void setArchitecture(RamMemoryArchitecture architecture) {
		this.architecture = architecture.getCod();
	}
	

	
}

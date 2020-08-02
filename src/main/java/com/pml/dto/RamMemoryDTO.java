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
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date deletedDate;
	private Integer equipmentType;
	private String manufacturer;
	private String model;
	private String description;
	private boolean itComposed;
	private Double sizeInGB;
	private Integer architecture;
	
	
	public RamMemoryDTO() {
	}
	
	public RamMemoryDTO(RamMemory ramMemory) {
		this.id = ramMemory.getId();
		this.createdDate = ramMemory.getCreatedDate();
		this.lastModifiedDate = ramMemory.getLastModifiedDate();
		this.deletedDate = ramMemory.getDeletedDate();
		this.equipmentType = ramMemory.getEquipmentType().getCod();
		this.manufacturer = ramMemory.getManufacturer();
		this.model = ramMemory.getModel();
		this.description = ramMemory.getDescription();
		this.itComposed = ramMemory.isItComposed();
		this.sizeInGB = ramMemory.getSizeInGB();
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
	
	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
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

	public boolean isItComposed() {
		return itComposed;
	}

	public void setItComposed(boolean isItComposed) {
		this.itComposed = isItComposed;
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
	

	
}

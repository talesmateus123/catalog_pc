package com.pml.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.RamMemoryArchitecture;

@Entity
public class RamMemory extends Electronic {
	private static final long serialVersionUID = 1L;
	@NotNull
	private Double sizeInMB;
	@NotNull
	private Integer architecture;
	@ManyToOne
	@JoinColumn(name = "computer_id")
	@JsonBackReference
	private Computer computer;
	
	public RamMemory() {
		super();
		this.setEquipmentType(EquipmentType.RAM_MEMORY);
		this.setItComposed(false);
	}
	
	public RamMemory(Long id, Date createdDate, Date lastModifiedDate, String manufacturer,
			String model, String description, boolean itWorks, Double sizeInMB, RamMemoryArchitecture architecture, 
			Computer computer) {
		super(id, createdDate, lastModifiedDate, EquipmentType.RAM_MEMORY, manufacturer, model, description, itWorks, false);
		this.sizeInMB = sizeInMB;
		this.architecture = architecture.getCod(); 
		this.computer = computer;
		this.setItComposed(false);
	}
	
	public Double getSizeInMB() {
		return sizeInMB;
	}
	
	public void setSizeInMB(Double sizeInMB) {
		this.sizeInMB = sizeInMB;
	}
	
	public RamMemoryArchitecture getArchitecture() {
		return RamMemoryArchitecture.toEnum(architecture);
	}
	
	public void setArchitecture(RamMemoryArchitecture architecture) {
		this.architecture = architecture.getCod();
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	
}

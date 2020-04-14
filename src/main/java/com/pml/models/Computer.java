/** 
 * This is the class "Computer", extended by the abstract class "Machine". Which will be to represent a computer.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Computer extends Machine{
	private static final long serialVersionUID = 1L;
	private String motherBoardName;
	@NotEmpty
	private String memoryType;
	private Float memorySize;
	@NotEmpty
	private String hdType;
	private Float hdSize;
	@NotEmpty
	private String processorModel;
	@NotEmpty
	@Size(max = 10)
	private String processorArchitecture;
	private Boolean hasCdBurner;
	@NotEmpty
	private String cabinetModel;
	@NotEmpty
	private String operatingSystem;
	@Size(max = 10)
	private String operatingSystemArchitecture;
	
	@ManyToMany(mappedBy = "computers")
	private List<ComputerUser> computerUsers = new ArrayList<>();
	
	
	public Computer() {
		super();
		this.setMachineType("computer");
	}
	
	public Computer(String motherBoardName, @NotEmpty String memoryType, Float memorySize, @NotEmpty String hdType,
			Float hdSize, @NotEmpty String processorModel, @NotEmpty @Size(max = 10) String processorArchitecture,
			Boolean hasCdBurner, @NotEmpty String cabinetModel, @NotEmpty String operatingSystem,
			@Size(max = 10) String operatingSystemArchitecture, List<ComputerUser> computerUsers) {
		super();
		this.motherBoardName = motherBoardName;
		this.memoryType = memoryType;
		this.memorySize = memorySize;
		this.hdType = hdType;
		this.hdSize = hdSize;
		this.processorModel = processorModel;
		this.processorArchitecture = processorArchitecture;
		this.hasCdBurner = hasCdBurner;
		this.cabinetModel = cabinetModel;
		this.operatingSystem = operatingSystem;
		this.operatingSystemArchitecture = operatingSystemArchitecture;
		this.computerUsers = computerUsers;
		this.setMachineType("computer");
	}

	public String getMotherBoardName() {
		return motherBoardName;
	}

	public void setMotherBoardName(String motherBoardName) {
		this.motherBoardName = motherBoardName;
	}

	public String getMemoryType() {
		return memoryType;
	}

	public void setMemoryType(String memoryType) {
		this.memoryType = memoryType;
	}

	public Float getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(Float memorySize) {
		this.memorySize = memorySize;
	}

	public String getHdType() {
		return hdType;
	}

	public void setHdType(String hdType) {
		this.hdType = hdType;
	}

	public Float getHdSize() {
		return hdSize;
	}

	public void setHdSize(Float hdSize) {
		this.hdSize = hdSize;
	}

	public String getProcessorModel() {
		return processorModel;
	}

	public void setProcessorModel(String processorModel) {
		this.processorModel = processorModel;
	}

	public String getProcessorArchitecture() {
		return processorArchitecture;
	}

	public void setProcessorArchitecture(String processorArchitecture) {
		this.processorArchitecture = processorArchitecture;
	}

	public Boolean getHasCdBurner() {
		return hasCdBurner;
	}

	public void setHasCdBurner(Boolean hasCdBurner) {
		this.hasCdBurner = hasCdBurner;
	}

	public String getCabinetModel() {
		return cabinetModel;
	}

	public void setCabinetModel(String cabinetModel) {
		this.cabinetModel = cabinetModel;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getOperatingSystemArchitecture() {
		return operatingSystemArchitecture;
	}

	public void setOperatingSystemArchitecture(String operatingSystemArchitecture) {
		this.operatingSystemArchitecture = operatingSystemArchitecture;
	}

	public List<ComputerUser> getComputerUsers() {
		return computerUsers;
	}

	public void setComputerUsers(List<ComputerUser> computerUsers) {
		this.computerUsers = computerUsers;
	}
	
	
}

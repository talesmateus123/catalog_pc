/** 
 * This is the class "Computer", extended by the abstract class "Machine". Which will be to represent a computer.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitor_id")
	// A monitor can be used with a unique computer.
	private Monitor monitor;
	@JsonBackReference
	@ManyToMany
	@JoinTable(
	  name = "computer_computer_user", 
	  joinColumns = @JoinColumn(name = "computer_patrimonyid"), 
	  inverseJoinColumns = @JoinColumn(name = "computer_user_id"))
	// A computer can be used by several users, because the company uses the Active Directory server.
	private List<ComputerUser> computerUsers;

	public Computer() {
		super();
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
	
	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	
	public List<ComputerUser> getComputerUsers() {
		return computerUsers;
	}

	public void setComputerUsers(List<ComputerUser> computerUsers) {
		this.computerUsers = computerUsers;
	}
	
	
}

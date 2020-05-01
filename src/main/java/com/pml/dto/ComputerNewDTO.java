package com.pml.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.OperatingSystem;

public class ComputerNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 10, message = "The text of this field must contain between 4 and 10 characters")
	private String patrimonyId;
	private String manufacturer;
	private String model;
	@Size(max = 100, message = "The text must contain a maximum of 100 characters")
	private String description;
	private boolean itWorks;
	private String ipAddress;
	private String hostName;
	private String motherBoardName;
	private Boolean hasCdBurner;
	private String cabinetModel;
	private Integer operatingSystem;
	private Integer operatingSystemArchitecture;
	@NotNull(message = "This field is mandatory")
	private boolean onTheDomain;
	
	private Long monitorId;
	private Long processorId;
	@NotNull(message = "This field is mandatory")	
	private Long sectorId;
	private List<Long> ramMemoriesId;
	private List<Long> storageDevicesId;
	private List<Long> computerUsersId = new ArrayList<>();
	
	public ComputerNewDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatrimonyId() {
		return patrimonyId;
	}
	
	public void setPatrimonyId(String patrimonyId) {
		this.patrimonyId = patrimonyId;
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
	
	public Long getSectorId() {
		return sectorId;
	}
	
	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}
	
	public boolean isItWorks() {
		return itWorks;
	}

	public void setItWorks(boolean itWorks) {
		this.itWorks = itWorks;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getMotherBoardName() {
		return motherBoardName;
	}

	public void setMotherBoardName(String motherBoardName) {
		this.motherBoardName = motherBoardName;
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

	public OperatingSystem getOperatingSystem() {
		return OperatingSystem.toEnum(operatingSystem);
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem.getCod();
	}

	public ArchitectureType getOperatingSystemArchitecture() {
		return ArchitectureType.toEnum(this.operatingSystemArchitecture);
	}

	public void setOperatingSystemArchitecture(ArchitectureType operatingSystemArchitecture) {
		this.operatingSystemArchitecture = operatingSystemArchitecture.getCod();
	}

	public boolean isOnTheDomain() {
		return onTheDomain;
	}

	public void setOnTheDomain(boolean onTheDomain) {
		this.onTheDomain = onTheDomain;
	}	

	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	public Long getProcessorId() {
		return processorId;
	}

	public void setProcessorId(Long processorId) {
		this.processorId = processorId;
	}	
	
	public List<Long> getRamMemoriesId() {
		return ramMemoriesId;
	}

	public void setRamMemoriesId(List<Long> ramMemoriesId) {
		this.ramMemoriesId = ramMemoriesId;
	}

	public List<Long> getStorageDevicesId() {
		return storageDevicesId;
	}

	public void setStorageDevicesId(List<Long> storageDevicesId) {
		this.storageDevicesId = storageDevicesId;
	}

	public List<Long> getComputerUsersId() {
		return computerUsersId;
	}

	public void setComputerUsersId(List<Long> computerUsersId) {
		this.computerUsersId = computerUsersId;
	}

	
}

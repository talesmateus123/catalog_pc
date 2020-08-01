package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.Computer;
import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.ComputerType;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.OperatingSystem;

public class ComputerDTO implements Serializable {
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
	private String patrimonyId;
	private String ipAddress;
	private String macAddress;
	private String hostName;
	private boolean online;
	private String motherBoardName;
	private Boolean hasCdBurner;
	private String cabinetModel;
	private Integer operatingSystem;
	private Integer operatingSystemArchitecture;
	private Integer computerType;
	private boolean onTheDomain;
	private boolean personalComputer;
	private Double totalRamMemory;
	private Double totalStorageMemory;
	
	public ComputerDTO() {
	}
	
	public ComputerDTO(Computer computer) {
		this.id = computer.getId(); 
		this.createdDate = computer.getCreatedDate();
		this.lastModifiedDate = computer.getLastModifiedDate();
		this.equipmentType = (computer.getEquipmentType() != null) ? computer.getEquipmentType().getCod() : null;
		this.manufacturer = computer.getManufacturer();
		this.model = computer.getModel();
		this.description = computer.getDescription();
		this.itWorks = computer.isItWorks();
		this.itComposed = computer.isItComposed();
		this.patrimonyId = computer.getPatrimonyId();
		this.ipAddress = computer.getIpAddress();
		this.macAddress = computer.getMacAddress();
		this.hostName = computer.getHostName();
		this.online = computer.isOnline();
		this.motherBoardName = computer.getMotherBoardName(); 
		this.hasCdBurner = computer.getHasCdBurner();
		this.operatingSystem = (computer.getOperatingSystem() != null) ? computer.getOperatingSystem().getCod() : null;
		this.operatingSystemArchitecture = (computer.getOperatingSystemArchitecture() != null) ? computer.getOperatingSystemArchitecture().getCod() : null;
		this.computerType = (computer.getComputerType() != null) ? computer.getComputerType().getCod() : null;
		this.onTheDomain = computer.isOnTheDomain();
		this.personalComputer = computer.isPersonalComputer();
		this.totalRamMemory = computer.getTotalRamMemory();
		this.totalStorageMemory = computer.getTotalStorageMemory();
		this.cabinetModel = computer.getCabinetModel();;
		this.totalRamMemory = computer.getTotalRamMemory();
		this.totalStorageMemory = computer.getTotalStorageMemory();
		this.itComposed = computer.isItComposed();
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

	public void setItComposed(boolean itComposed) {
		this.itComposed = itComposed;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}
	
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
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

	public ComputerType getComputerType() {
		return ComputerType.toEnum(this.computerType);
	}

	public void setComputerType(ComputerType computerType) {
		this.computerType = computerType.getCod();
	}

	public boolean isOnTheDomain() {
		return onTheDomain;
	}

	public void setOnTheDomain(boolean onTheDomain) {
		this.onTheDomain = onTheDomain;
	}
	
	public boolean isPersonalComputer() {
		return personalComputer;
	}

	public void setIsPersonalComputer(boolean personalComputer) {
		this.personalComputer = personalComputer;
	}

	public Double getTotalRamMemory() {
		return totalRamMemory;
	}

	public void setTotalRamMemory(Double totalRamMemory) {
		this.totalRamMemory = totalRamMemory;
	}
	
	public Double getTotalStorageMemory() {
		return totalStorageMemory;
	}

	public void setTotalStorageMemory(Double totalStorageMemory) {
		this.totalStorageMemory = totalStorageMemory;
	}
	

	
}

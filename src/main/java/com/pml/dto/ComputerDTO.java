package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.Computer;
import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.OperatingSystemType;
import com.pml.domain.enums.Sector;

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
	private boolean itWorks = true; 
	private boolean itComposed;
	private String patrimonyId = "";
	private Integer sector;
	private String ipAddress;
	private String hostName;
	private String motherBoardName;
	private Boolean hasCdBurner;
	private String cabinetModel;
	private Integer operatingSystem;
	private Integer operatingSystemArchitecture;
	private boolean onTheDomain;
	private Long processorId;
	private Double totalRamMemory;
	private Double totalStorageMemory;
	
	public ComputerDTO() {
	}
	
	public ComputerDTO(Computer computer) {
		this.id = computer.getId();
		this.createdDate = computer.getCreatedDate();
		this.lastModifiedDate = computer.getLastModifiedDate();
		this.equipmentType = computer.getEquipmentType().getCod();
		this.manufacturer = computer.getManufacturer();
		this.model = computer.getModel();
		this.description = computer.getDescription();
		this.itWorks = computer.isItWorks();
		this.itComposed = computer.isItComposed();
		this.patrimonyId = computer.getPatrimonyId();
		this.sector = computer.getSector().getCod();
		this.ipAddress = computer.getIpAddress();
		this.hostName = computer.getHostName();
		this.motherBoardName = computer.getMotherBoardName();
		this.processorId = computer.getProcessor().getId();
		this.hasCdBurner = computer.getHasCdBurner();
		this.operatingSystem = computer.getOperatingSystem().getCod();
		this.operatingSystemArchitecture = computer.getOperatingSystemArchitecture().getCod();
		this.onTheDomain = computer.isOnTheDomain();
		this.cabinetModel = computer.getCabinetModel();;
		this.totalRamMemory = computer.getTotalRamMemory();
		this.totalStorageMemory = computer.getTotalStorageMemory();
		this.itComposed = computer.isItComposed();
	}
	
	public ComputerDTO(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, String manufacturer, 
			String model, String description, Integer sector, boolean itWorks, String ipAddress, String hostName,
			String motherBoardName, Long processorId, Double totalRamMemory, Double totalStorageMemory, 
			Boolean hasCdBurner, String cabinetModel, Integer operatingSystem, Integer operatingSystemArchitecture, 
			boolean onTheDomain, EquipmentType equipmentType, boolean itComposed) {
		this.id = id;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.equipmentType = equipmentType.getCod();
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.itWorks = itWorks;
		this.itComposed = itComposed;
		this.patrimonyId = patrimonyId;
		this.sector = sector;
		this.ipAddress = ipAddress;
		this.hostName = hostName;
		this.motherBoardName = motherBoardName;
		this.processorId = processorId;		
		this.hasCdBurner = hasCdBurner;
		this.operatingSystem = operatingSystem;
		this.operatingSystemArchitecture = operatingSystemArchitecture;
		this.onTheDomain = onTheDomain;
		this.cabinetModel = cabinetModel;
		this.totalRamMemory = totalRamMemory;
		this.totalStorageMemory = totalStorageMemory;
		this.itComposed = itComposed;
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
	
	public Sector getSector() {
		return Sector.toEnum(sector);
	}
	
	public void setSector(Sector location) {
		this.sector = location.getCod();
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

	public OperatingSystemType getOperatingSystem() {
		return OperatingSystemType.toEnum(operatingSystem);
	}

	public void setOperatingSystem(OperatingSystemType operatingSystem) {
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
	
	public Long getProcessorId() {
		return processorId;
	}

	public void setProcessorId(Long processorId) {
		this.processorId = processorId;
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

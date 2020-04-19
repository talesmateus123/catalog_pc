package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.Computer;
import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.HardDiskType;
import com.pml.domain.enums.OperatingSystemType;
import com.pml.domain.enums.RamMemoryType;
import com.pml.domain.enums.Sector;

public class ComputerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 10, message = "The text of this field must contain between 4 and 10 characters")
	@Column(unique = true)
	private String patrimonyId;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date lastModifiedDate;
	private Integer equipmentType;
	@NotEmpty(message = "This field is mandatory")
	private String manufacturer;
	@NotEmpty(message = "This field is mandatory")
	private String model;
	@Size(max = 100, message = "The text must contain a maximum of 100 characters")
	private String description;
	private Integer sector;
	private boolean itWorks;
	private boolean itComposed;
	private String ipAddress;
	private String motherBoardName;
	@NotNull(message = "This field is mandatory")
	private Integer memoryType;
	private Double memorySize;
	private Integer hdType;
	private Double hdSize;
	private String processorModel;
	@NotNull(message = "This field is mandatory")
	private Integer processorArchitecture;
	@NotNull(message = "This field is mandatory")
	private Boolean hasCdBurner;
	private String cabinetModel;
	private Integer operatingSystem;
	private Integer operatingSystemArchitecture;
	@NotNull(message = "This field is mandatory")
	private boolean onTheDomain;
	
	public ComputerDTO() {
		this.setEquipmentType(EquipmentType.COMPUTER);
		this.setItComposed(true);
	}
	
	public ComputerDTO(Computer computer) {
		this.id = computer.getId();
		this.patrimonyId = computer.getPatrimonyId();
		this.createdDate = computer.getCreatedDate();
		this.lastModifiedDate = computer.getLastModifiedDate();
		this.equipmentType = computer.getEquipmentType().getCod();
		this.manufacturer = computer.getManufacturer();
		this.model = computer.getModel();
		this.description = computer.getDescription();
		this.sector = computer.getSector().getCod();
		this.itWorks = computer.isItWorks();
		this.itComposed = computer.isItComposed();
		this.ipAddress = computer.getIpAddress();
		this.motherBoardName = computer.getMotherBoardName();
		this.memoryType = computer.getMemoryType().getCod();
		this.memorySize = computer.getMemorySize();
		this.hdType = computer.getHdType().getCod();
		this.hdSize = computer.getHdSize();
		this.processorModel = computer.getProcessorModel();
		this.processorArchitecture = computer.getProcessorArchitecture().getCod();
		this.hasCdBurner = computer.getHasCdBurner();
		this.cabinetModel = computer.getCabinetModel();
		this.operatingSystem = computer.getOperatingSystem().getCod();
		this.operatingSystemArchitecture = computer.getOperatingSystemArchitecture().getCod();
		this.onTheDomain = computer.isOnTheDomain();
		this.setEquipmentType(EquipmentType.COMPUTER);
		this.setItComposed(true);
	}
	
	public ComputerDTO(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, String manufacturer, 
			String model, String description, Integer sector, boolean itWorks, String ipAddress,
			String motherBoardName, Integer memoryType, Double memorySize, Integer hdType, Double hdSize,
			String processorModel, Integer processorArchitecture, Boolean hasCdBurner, String cabinetModel,
			Integer operatingSystem, Integer operatingSystemArchitecture, boolean onTheDomain) {
		this.id = id;
		this.patrimonyId = patrimonyId;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.sector = sector;
		this.itWorks = itWorks;
		this.ipAddress = ipAddress;
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
		this.onTheDomain = onTheDomain;
		this.setEquipmentType(EquipmentType.COMPUTER);
		this.setItComposed(true);
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

	public void setItWorks(boolean isItWorks) {
		this.itWorks = isItWorks;
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

	public String getMotherBoardName() {
		return motherBoardName;
	}

	public void setMotherBoardName(String motherBoardName) {
		this.motherBoardName = motherBoardName;
	}

	public RamMemoryType getMemoryType() {
		return RamMemoryType.toEnum(memoryType);
	}

	public void setMemoryType(RamMemoryType memoryType) {
		this.memoryType = memoryType.getCod();
	}

	public Double getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(Double memorySize) {
		this.memorySize = memorySize;
	}

	public HardDiskType getHdType() {
		return HardDiskType.toEnum(hdType);
	}

	public void setHdType(HardDiskType hdType) {
		this.hdType = hdType.getCod();
	}

	public Double getHdSize() {
		return hdSize;
	}

	public void setHdSize(Double hdSize) {
		this.hdSize = hdSize;
	}

	public String getProcessorModel() {
		return processorModel;
	}

	public void setProcessorModel(String processorModel) {
		this.processorModel = processorModel;
	}

	public ArchitectureType getProcessorArchitecture() {
		return ArchitectureType.toEnum(processorArchitecture);
	}

	public void setProcessorArchitecture(ArchitectureType processorArchitecture) {
		this.processorArchitecture = processorArchitecture.getCod();
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

}

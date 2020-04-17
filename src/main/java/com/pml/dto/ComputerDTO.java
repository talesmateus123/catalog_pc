package com.pml.dto;

import java.util.Date;

import com.pml.domain.Computer;
import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.HardDiskType;
import com.pml.domain.enums.OperatingSystemType;
import com.pml.domain.enums.RamMemoryType;

public class ComputerDTO extends MachineDTO{	
	private String ipAddress;
	private String motherBoardName;
	private Integer memoryType;
	private Double memorySize;
	private Integer hdType;
	private Double hdSize;
	private String processorModel;
	private Integer processorArchitecture;
	private Boolean hasCdBurner;
	private String cabinetModel;
	private Integer operatingSystem;
	private Integer operatingSystemArchitecture;
	private boolean isOnTheDomain;
	
	public ComputerDTO() {	
	}
	
	public ComputerDTO(Computer computer) {
		super(computer.getId() , computer.getPatrimonyId(), computer.getCreatedDate(), computer.getModifiedDate(), computer.getMachineType().getCod(), 
				computer.getManufacturer(), computer.getModel(), computer.getDescription(), computer.getSector().getCod(), computer.isItWorking());
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
	}
	
	public ComputerDTO(Long id, String patrimonyId, Date createdDate, Date modifiedDate, Integer machineType,
			String manufacturer, String model, String description, Integer sector, boolean isItWorking, String ipAddress,
			String motherBoardName, Integer memoryType, Double memorySize, Integer hdType, Double hdSize,
			String processorModel, Integer processorArchitecture, Boolean hasCdBurner, String cabinetModel,
			Integer operatingSystem, Integer operatingSystemArchitecture) {
		super(id, patrimonyId, createdDate, modifiedDate, machineType, manufacturer, model, description, sector, isItWorking);
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

	public Boolean hasCdBurner() {
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
		return isOnTheDomain;
	}

	public void setOnTheDomain(boolean isOnTheDomain) {
		this.isOnTheDomain = isOnTheDomain;
	}

}

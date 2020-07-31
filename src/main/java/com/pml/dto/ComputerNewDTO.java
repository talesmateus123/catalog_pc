package com.pml.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.OperatingSystem;
import com.pml.domain.enums.RamMemoryArchitecture;
import com.pml.domain.enums.StorageDeviceArchitecture;
import com.pml.domain.enums.StorageDeviceType;

public class ComputerNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String patrimonyId;
	private String manufacturer;
	private String model;
	@Size(max = 100, message = "{description.size}")
	private String description;
	@NotNull(message = "{not.null}")
	private Boolean itWorks;
	@Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "{ipAddress.pattern}")
	private String ipAddress;
	@Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$", message = "{macAddress.pattern}")
	private String macAddress;
	private String hostName;
	private String motherBoardName;
	private Boolean hasCdBurner;
	private String cabinetModel;
	private Integer operatingSystem;
	private Integer operatingSystemArchitecture;
	@NotNull(message = "{not.null}")
	private Boolean onTheDomain;
	private boolean personalComputer;
	private Double totalRamMemory;
	private Double totalStorageMemory;
	private Long monitorId;
	@NotNull(message = "{not.null}")
	private Integer sectorId;
	private List<Long> computerUsersId = new ArrayList<>();

	private Long processor_id;
	private String processor_manufacturer;
	private String processor_model;
	private String processor_description;
	private Boolean processor_itWorks;
	private String processor_processorName;
	private Integer processor_architecture;

	// Ram memories attributes
	private Long ramMemory1_id;
	private String ramMemory1_manufacturer;
	private String ramMemory1_model;
	private String ramMemory1_description;
	private Boolean ramMemory1_itWorks;
	private Double ramMemory1_sizeInGB;
	private Integer ramMemory1_architecture;

	private Long ramMemory2_id;
	private String ramMemory2_manufacturer;
	private String ramMemory2_model;
	private String ramMemory2_description;
	private Boolean ramMemory2_itWorks;
	private Double ramMemory2_sizeInGB;
	private Integer ramMemory2_architecture;

	private Long ramMemory3_id;
	private String ramMemory3_manufacturer;
	private String ramMemory3_model;
	private String ramMemory3_description;
	private Boolean ramMemory3_itWorks;
	private Double ramMemory3_sizeInGB;
	private Integer ramMemory3_architecture;

	private Long ramMemory4_id;
	private String ramMemory4_manufacturer;
	private String ramMemory4_model;
	private String ramMemory4_description;
	private Boolean ramMemory4_itWorks;
	private Double ramMemory4_sizeInGB;
	private Integer ramMemory4_architecture;

	private Long ramMemory5_id;
	private String ramMemory5_manufacturer;
	private String ramMemory5_model;
	private String ramMemory5_description;
	private Boolean ramMemory5_itWorks;
	private Double ramMemory5_sizeInGB;
	private Integer ramMemory5_architecture;

	private Long ramMemory6_id;
	private String ramMemory6_manufacturer;
	private String ramMemory6_model;
	private String ramMemory6_description;
	private Boolean ramMemory6_itWorks;
	private Double ramMemory6_sizeInGB;
	private Integer ramMemory6_architecture;

	private Long ramMemory7_id;
	private String ramMemory7_manufacturer;
	private String ramMemory7_model;
	private String ramMemory7_description;
	private Boolean ramMemory7_itWorks;
	private Double ramMemory7_sizeInGB;
	private Integer ramMemory7_architecture;

	private Long ramMemory8_id;
	private String ramMemory8_manufacturer;
	private String ramMemory8_model;
	private String ramMemory8_description;
	private Boolean ramMemory8_itWorks;
	private Double ramMemory8_sizeInGB;
	private Integer ramMemory8_architecture;
	
  // Storage devices attributes
	private Long storageDevice1_id;
	private String storageDevice1_manufacturer;
	private String storageDevice1_model;
	private String storageDevice1_description;
	private Boolean storageDevice1_itWorks;
	private Double storageDevice1_sizeInGB;
	private Integer storageDevice1_architecture;
	private Integer storageDevice1_type;

	private Long storageDevice2_id;
	private String storageDevice2_manufacturer;
	private String storageDevice2_model;
	private String storageDevice2_description;
	private Boolean storageDevice2_itWorks;
	private Double storageDevice2_sizeInGB;
	private Integer storageDevice2_architecture;
	private Integer storageDevice2_type;
	
	private Long storageDevice3_id;
	private String storageDevice3_manufacturer;
	private String storageDevice3_model;
	private String storageDevice3_description;
	private Boolean storageDevice3_itWorks;
	private Double storageDevice3_sizeInGB;
	private Integer storageDevice3_architecture;
	private Integer storageDevice3_type;

	private Long storageDevice4_id;
	private String storageDevice4_manufacturer;
	private String storageDevice4_model;
	private String storageDevice4_description;
	private Boolean storageDevice4_itWorks;
	private Double storageDevice4_sizeInGB;
	private Integer storageDevice4_architecture;
	private Integer storageDevice4_type;	

	private Long storageDevice5_id;
	private String storageDevice5_manufacturer;
	private String storageDevice5_model;
	private String storageDevice5_description;
	private Boolean storageDevice5_itWorks;
	private Double storageDevice5_sizeInGB;
	private Integer storageDevice5_architecture;
	private Integer storageDevice5_type;

	private Long storageDevice6_id;
	private String storageDevice6_manufacturer;
	private String storageDevice6_model;
	private String storageDevice6_description;
	private Boolean storageDevice6_itWorks;
	private Double storageDevice6_sizeInGB;
	private Integer storageDevice6_architecture;
	private Integer storageDevice6_type;
	
	private Long storageDevice7_id;
	private String storageDevice7_manufacturer;
	private String storageDevice7_model;
	private String storageDevice7_description;
	private Boolean storageDevice7_itWorks;
	private Double storageDevice7_sizeInGB;
	private Integer storageDevice7_architecture;
	private Integer storageDevice7_type;

	private Long storageDevice8_id;
	private String storageDevice8_manufacturer;
	private String storageDevice8_model;
	private String storageDevice8_description;
	private Boolean storageDevice8_itWorks;
	private Double storageDevice8_sizeInGB;
	private Integer storageDevice8_architecture;
	private Integer storageDevice8_type;
		
	public ComputerNewDTO() {
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
	
	public Integer getSectorId() {
		return sectorId;
	}
	
	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}
	
	public Boolean isItWorks() {
		return itWorks;
	}

	public void setItWorks(Boolean itWorks) {
		this.itWorks = itWorks;
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

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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

	public Boolean isOnTheDomain() {
		return onTheDomain;
	}

	public void setOnTheDomain(Boolean onTheDomain) {
		this.onTheDomain = onTheDomain;
	}

	public Double getTotalRamMemory() {
		return totalRamMemory;
	}
	
	public boolean isPersonalComputer() {
		return personalComputer;
	}

	public void setIsPersonalComputer(boolean personalComputer) {
		this.personalComputer = personalComputer;
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

	public Long getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}

	public List<Long> getComputerUsersId() {
		return computerUsersId;
	}

	public void setComputerUsersId(List<Long> computerUsersId) {
		this.computerUsersId = computerUsersId;
	}

	public Long getProcessor_id() {
		return processor_id;
	}

	public void setProcessor_id(Long processor_id) {
		this.processor_id = processor_id;
	}

	public String getProcessor_manufacturer() {
		return processor_manufacturer;
	}

	public void setProcessor_manufacturer(String processor_manufacturer) {
		this.processor_manufacturer = processor_manufacturer;
	}

	public String getProcessor_model() {
		return processor_model;
	}

	public void setProcessor_model(String processor_model) {
		this.processor_model = processor_model;
	}

	public String getProcessor_description() {
		return processor_description;
	}

	public void setProcessor_description(String processor_description) {
		this.processor_description = processor_description;
	}

	public Boolean isProcessor_itWorks() {
		return processor_itWorks;
	}

	public void setProcessor_itWorks(Boolean processor_itWorks) {
		this.processor_itWorks = processor_itWorks;
	}

	public String getProcessor_processorName() {
		return processor_processorName;
	}

	public void setProcessor_processorName(String processor_processorName) {
		this.processor_processorName = processor_processorName;
	}

	public ArchitectureType getProcessor_architecture() {
		return ArchitectureType.toEnum(this.processor_architecture);
	}

	public void setProcessor_architecture(ArchitectureType processor_architecture) {
		this.processor_architecture = processor_architecture.getCod();
	}

	public Long getRamMemory1_id() {
		return ramMemory1_id;
	}

	public void setRamMemory1_id(Long ramMemory1_id) {
		this.ramMemory1_id = ramMemory1_id;
	}

	public String getRamMemory1_manufacturer() {
		return ramMemory1_manufacturer;
	}

	public void setRamMemory1_manufacturer(String ramMemory1_manufacturer) {
		this.ramMemory1_manufacturer = ramMemory1_manufacturer;
	}

	public String getRamMemory1_model() {
		return ramMemory1_model;
	}

	public void setRamMemory1_model(String ramMemory1_model) {
		this.ramMemory1_model = ramMemory1_model;
	}

	public String getRamMemory1_description() {
		return ramMemory1_description;
	}

	public void setRamMemory1_description(String ramMemory1_description) {
		this.ramMemory1_description = ramMemory1_description;
	}

	public Boolean getRamMemory1_itWorks() {
		return ramMemory1_itWorks;
	}

	public void setRamMemory1_itWorks(Boolean ramMemory1_itWorks) {
		this.ramMemory1_itWorks = ramMemory1_itWorks;
	}

	public Double getRamMemory1_sizeInGB() {
		return ramMemory1_sizeInGB;
	}

	public void setRamMemory1_sizeInGB(Double ramMemory1_sizeInGB) {
		this.ramMemory1_sizeInGB = ramMemory1_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory1_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory1_architecture);
	}

	public void setRamMemory1_architecture(RamMemoryArchitecture ramMemory1_architecture) {
		this.ramMemory1_architecture = ramMemory1_architecture.getCod();
	}

	public Long getRamMemory2_id() {
		return ramMemory2_id;
	}

	public void setRamMemory2_id(Long ramMemory2_id) {
		this.ramMemory2_id = ramMemory2_id;
	}

	public String getRamMemory2_manufacturer() {
		return ramMemory2_manufacturer;
	}

	public void setRamMemory2_manufacturer(String ramMemory2_manufacturer) {
		this.ramMemory2_manufacturer = ramMemory2_manufacturer;
	}

	public String getRamMemory2_model() {
		return ramMemory2_model;
	}

	public void setRamMemory2_model(String ramMemory2_model) {
		this.ramMemory2_model = ramMemory2_model;
	}

	public String getRamMemory2_description() {
		return ramMemory2_description;
	}

	public void setRamMemory2_description(String ramMemory2_description) {
		this.ramMemory2_description = ramMemory2_description;
	}

	public Boolean getRamMemory2_itWorks() {
		return ramMemory2_itWorks;
	}

	public void setRamMemory2_itWorks(Boolean ramMemory2_itWorks) {
		this.ramMemory2_itWorks = ramMemory2_itWorks;
	}

	public Double getRamMemory2_sizeInGB() {
		return ramMemory2_sizeInGB;
	}

	public void setRamMemory2_sizeInGB(Double ramMemory2_sizeInGB) {
		this.ramMemory2_sizeInGB = ramMemory2_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory2_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory2_architecture);
	}

	public void setRamMemory2_architecture(RamMemoryArchitecture ramMemory2_architecture) {
		this.ramMemory2_architecture = ramMemory2_architecture.getCod();
	}

	public Long getRamMemory3_id() {
		return ramMemory3_id;
	}

	public void setRamMemory3_id(Long ramMemory3_id) {
		this.ramMemory3_id = ramMemory3_id;
	}

	public String getRamMemory3_manufacturer() {
		return ramMemory3_manufacturer;
	}

	public void setRamMemory3_manufacturer(String ramMemory3_manufacturer) {
		this.ramMemory3_manufacturer = ramMemory3_manufacturer;
	}

	public String getRamMemory3_model() {
		return ramMemory3_model;
	}

	public void setRamMemory3_model(String ramMemory3_model) {
		this.ramMemory3_model = ramMemory3_model;
	}

	public String getRamMemory3_description() {
		return ramMemory3_description;
	}

	public void setRamMemory3_description(String ramMemory3_description) {
		this.ramMemory3_description = ramMemory3_description;
	}

	public Boolean getRamMemory3_itWorks() {
		return ramMemory3_itWorks;
	}

	public void setRamMemory3_itWorks(Boolean ramMemory3_itWorks) {
		this.ramMemory3_itWorks = ramMemory3_itWorks;
	}

	public Double getRamMemory3_sizeInGB() {
		return ramMemory3_sizeInGB;
	}

	public void setRamMemory3_sizeInGB(Double ramMemory3_sizeInGB) {
		this.ramMemory3_sizeInGB = ramMemory3_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory3_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory3_architecture);
	}

	public void setRamMemory3_architecture(RamMemoryArchitecture ramMemory3_architecture) {
		this.ramMemory3_architecture = ramMemory3_architecture.getCod();
	}

	public Long getRamMemory4_id() {
		return ramMemory4_id;
	}

	public void setRamMemory4_id(Long ramMemory4_id) {
		this.ramMemory4_id = ramMemory4_id;
	}

	public String getRamMemory4_manufacturer() {
		return ramMemory4_manufacturer;
	}

	public void setRamMemory4_manufacturer(String ramMemory4_manufacturer) {
		this.ramMemory4_manufacturer = ramMemory4_manufacturer;
	}

	public String getRamMemory4_model() {
		return ramMemory4_model;
	}

	public void setRamMemory4_model(String ramMemory4_model) {
		this.ramMemory4_model = ramMemory4_model;
	}

	public String getRamMemory4_description() {
		return ramMemory4_description;
	}

	public void setRamMemory4_description(String ramMemory4_description) {
		this.ramMemory4_description = ramMemory4_description;
	}

	public Boolean getRamMemory4_itWorks() {
		return ramMemory4_itWorks;
	}

	public void setRamMemory4_itWorks(Boolean ramMemory4_itWorks) {
		this.ramMemory4_itWorks = ramMemory4_itWorks;
	}

	public Double getRamMemory4_sizeInGB() {
		return ramMemory4_sizeInGB;
	}

	public void setRamMemory4_sizeInGB(Double ramMemory4_sizeInGB) {
		this.ramMemory4_sizeInGB = ramMemory4_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory4_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory4_architecture);
	}

	public void setRamMemory4_architecture(RamMemoryArchitecture ramMemory4_architecture) {
		this.ramMemory4_architecture = ramMemory4_architecture.getCod();
	}

	public Long getRamMemory5_id() {
		return ramMemory5_id;
	}

	public void setRamMemory5_id(Long ramMemory5_id) {
		this.ramMemory5_id = ramMemory5_id;
	}

	public String getRamMemory5_manufacturer() {
		return ramMemory5_manufacturer;
	}

	public void setRamMemory5_manufacturer(String ramMemory5_manufacturer) {
		this.ramMemory5_manufacturer = ramMemory5_manufacturer;
	}

	public String getRamMemory5_model() {
		return ramMemory5_model;
	}

	public void setRamMemory5_model(String ramMemory5_model) {
		this.ramMemory5_model = ramMemory5_model;
	}

	public String getRamMemory5_description() {
		return ramMemory5_description;
	}

	public void setRamMemory5_description(String ramMemory5_description) {
		this.ramMemory5_description = ramMemory5_description;
	}

	public Boolean getRamMemory5_itWorks() {
		return ramMemory5_itWorks;
	}

	public void setRamMemory5_itWorks(Boolean ramMemory5_itWorks) {
		this.ramMemory5_itWorks = ramMemory5_itWorks;
	}

	public Double getRamMemory5_sizeInGB() {
		return ramMemory5_sizeInGB;
	}

	public void setRamMemory5_sizeInGB(Double ramMemory5_sizeInGB) {
		this.ramMemory5_sizeInGB = ramMemory5_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory5_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory5_architecture);
	}

	public void setRamMemory5_architecture(RamMemoryArchitecture ramMemory5_architecture) {
		this.ramMemory5_architecture = ramMemory5_architecture.getCod();
	}

	public Long getRamMemory6_id() {
		return ramMemory6_id;
	}

	public void setRamMemory6_id(Long ramMemory6_id) {
		this.ramMemory6_id = ramMemory6_id;
	}

	public String getRamMemory6_manufacturer() {
		return ramMemory6_manufacturer;
	}

	public void setRamMemory6_manufacturer(String ramMemory6_manufacturer) {
		this.ramMemory6_manufacturer = ramMemory6_manufacturer;
	}

	public String getRamMemory6_model() {
		return ramMemory6_model;
	}

	public void setRamMemory6_model(String ramMemory6_model) {
		this.ramMemory6_model = ramMemory6_model;
	}

	public String getRamMemory6_description() {
		return ramMemory6_description;
	}

	public void setRamMemory6_description(String ramMemory6_description) {
		this.ramMemory6_description = ramMemory6_description;
	}

	public Boolean getRamMemory6_itWorks() {
		return ramMemory6_itWorks;
	}

	public void setRamMemory6_itWorks(Boolean ramMemory6_itWorks) {
		this.ramMemory6_itWorks = ramMemory6_itWorks;
	}

	public Double getRamMemory6_sizeInGB() {
		return ramMemory6_sizeInGB;
	}

	public void setRamMemory6_sizeInGB(Double ramMemory6_sizeInGB) {
		this.ramMemory6_sizeInGB = ramMemory6_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory6_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory6_architecture);
	}

	public void setRamMemory6_architecture(RamMemoryArchitecture ramMemory6_architecture) {
		this.ramMemory6_architecture = ramMemory6_architecture.getCod();
	}

	public Long getRamMemory7_id() {
		return ramMemory7_id;
	}

	public void setRamMemory7_id(Long ramMemory7_id) {
		this.ramMemory7_id = ramMemory7_id;
	}

	public String getRamMemory7_manufacturer() {
		return ramMemory7_manufacturer;
	}

	public void setRamMemory7_manufacturer(String ramMemory7_manufacturer) {
		this.ramMemory7_manufacturer = ramMemory7_manufacturer;
	}

	public String getRamMemory7_model() {
		return ramMemory7_model;
	}

	public void setRamMemory7_model(String ramMemory7_model) {
		this.ramMemory7_model = ramMemory7_model;
	}

	public String getRamMemory7_description() {
		return ramMemory7_description;
	}

	public void setRamMemory7_description(String ramMemory7_description) {
		this.ramMemory7_description = ramMemory7_description;
	}

	public Boolean getRamMemory7_itWorks() {
		return ramMemory7_itWorks;
	}

	public void setRamMemory7_itWorks(Boolean ramMemory7_itWorks) {
		this.ramMemory7_itWorks = ramMemory7_itWorks;
	}

	public Double getRamMemory7_sizeInGB() {
		return ramMemory7_sizeInGB;
	}

	public void setRamMemory7_sizeInGB(Double ramMemory7_sizeInGB) {
		this.ramMemory7_sizeInGB = ramMemory7_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory7_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory7_architecture);
	}

	public void setRamMemory7_architecture(RamMemoryArchitecture ramMemory7_architecture) {
		this.ramMemory7_architecture = ramMemory7_architecture.getCod();
	}

	public Long getRamMemory8_id() {
		return ramMemory8_id;
	}

	public void setRamMemory8_id(Long ramMemory8_id) {
		this.ramMemory8_id = ramMemory8_id;
	}

	public String getRamMemory8_manufacturer() {
		return ramMemory8_manufacturer;
	}

	public void setRamMemory8_manufacturer(String ramMemory8_manufacturer) {
		this.ramMemory8_manufacturer = ramMemory8_manufacturer;
	}

	public String getRamMemory8_model() {
		return ramMemory8_model;
	}

	public void setRamMemory8_model(String ramMemory8_model) {
		this.ramMemory8_model = ramMemory8_model;
	}

	public String getRamMemory8_description() {
		return ramMemory8_description;
	}

	public void setRamMemory8_description(String ramMemory8_description) {
		this.ramMemory8_description = ramMemory8_description;
	}

	public Boolean getRamMemory8_itWorks() {
		return ramMemory8_itWorks;
	}

	public void setRamMemory8_itWorks(Boolean ramMemory8_itWorks) {
		this.ramMemory8_itWorks = ramMemory8_itWorks;
	}

	public Double getRamMemory8_sizeInGB() {
		return ramMemory8_sizeInGB;
	}

	public void setRamMemory8_sizeInGB(Double ramMemory8_sizeInGB) {
		this.ramMemory8_sizeInGB = ramMemory8_sizeInGB;
	}

	public RamMemoryArchitecture getRamMemory8_architecture() {
		return RamMemoryArchitecture.toEnum(ramMemory8_architecture);
	}

	public void setRamMemory8_architecture(RamMemoryArchitecture ramMemory8_architecture) {
		this.ramMemory8_architecture = ramMemory8_architecture.getCod();
	}

	public Long getStorageDevice1_id() {
		return storageDevice1_id;
	}

	public void setStorageDevice1_id(Long storageDevice1_id) {
		this.storageDevice1_id = storageDevice1_id;
	}

	public String getStorageDevice1_manufacturer() {
		return storageDevice1_manufacturer;
	}

	public void setStorageDevice1_manufacturer(String storageDevice1_manufacturer) {
		this.storageDevice1_manufacturer = storageDevice1_manufacturer;
	}

	public String getStorageDevice1_model() {
		return storageDevice1_model;
	}

	public void setStorageDevice1_model(String storageDevice1_model) {
		this.storageDevice1_model = storageDevice1_model;
	}

	public String getStorageDevice1_description() {
		return storageDevice1_description;
	}

	public void setStorageDevice1_description(String storageDevice1_description) {
		this.storageDevice1_description = storageDevice1_description;
	}

	public Boolean getStorageDevice1_itWorks() {
		return storageDevice1_itWorks;
	}

	public void setStorageDevice1_itWorks(Boolean storageDevice1_itWorks) {
		this.storageDevice1_itWorks = storageDevice1_itWorks;
	}

	public Double getStorageDevice1_sizeInGB() {
		return storageDevice1_sizeInGB;
	}

	public void setStorageDevice1_sizeInGB(Double storageDevice1_sizeInGB) {
		this.storageDevice1_sizeInGB = storageDevice1_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice1_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice1_architecture);
	}

	public void setStorageDevice1_architecture(StorageDeviceArchitecture storageDevice1_architecture) {
		this.storageDevice1_architecture = storageDevice1_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice1_type() {
		return StorageDeviceType.toEnum(storageDevice1_type);
	}

	public void setStorageDevice1_type(StorageDeviceType storageDevice1_type) {
		this.storageDevice1_type = storageDevice1_type.getCod();
	}

	public Long getStorageDevice2_id() {
		return storageDevice2_id;
	}

	public void setStorageDevice2_id(Long storageDevice2_id) {
		this.storageDevice2_id = storageDevice2_id;
	}

	public String getStorageDevice2_manufacturer() {
		return storageDevice2_manufacturer;
	}

	public void setStorageDevice2_manufacturer(String storageDevice2_manufacturer) {
		this.storageDevice2_manufacturer = storageDevice2_manufacturer;
	}

	public String getStorageDevice2_model() {
		return storageDevice2_model;
	}

	public void setStorageDevice2_model(String storageDevice2_model) {
		this.storageDevice2_model = storageDevice2_model;
	}

	public String getStorageDevice2_description() {
		return storageDevice2_description;
	}

	public void setStorageDevice2_description(String storageDevice2_description) {
		this.storageDevice2_description = storageDevice2_description;
	}

	public Boolean getStorageDevice2_itWorks() {
		return storageDevice2_itWorks;
	}

	public void setStorageDevice2_itWorks(Boolean storageDevice2_itWorks) {
		this.storageDevice2_itWorks = storageDevice2_itWorks;
	}

	public Double getStorageDevice2_sizeInGB() {
		return storageDevice2_sizeInGB;
	}

	public void setStorageDevice2_sizeInGB(Double storageDevice2_sizeInGB) {
		this.storageDevice2_sizeInGB = storageDevice2_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice2_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice2_architecture);
	}

	public void setStorageDevice2_architecture(StorageDeviceArchitecture storageDevice2_architecture) {
		this.storageDevice2_architecture = storageDevice2_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice2_type() {
		return StorageDeviceType.toEnum(storageDevice2_type);
	}

	public void setStorageDevice2_type(StorageDeviceType storageDevice2_type) {
		this.storageDevice2_type = storageDevice2_type.getCod();
	}

	public Long getStorageDevice3_id() {
		return storageDevice3_id;
	}

	public void setStorageDevice3_id(Long storageDevice3_id) {
		this.storageDevice3_id = storageDevice3_id;
	}

	public String getStorageDevice3_manufacturer() {
		return storageDevice3_manufacturer;
	}

	public void setStorageDevice3_manufacturer(String storageDevice3_manufacturer) {
		this.storageDevice3_manufacturer = storageDevice3_manufacturer;
	}

	public String getStorageDevice3_model() {
		return storageDevice3_model;
	}

	public void setStorageDevice3_model(String storageDevice3_model) {
		this.storageDevice3_model = storageDevice3_model;
	}

	public String getStorageDevice3_description() {
		return storageDevice3_description;
	}

	public void setStorageDevice3_description(String storageDevice3_description) {
		this.storageDevice3_description = storageDevice3_description;
	}

	public Boolean getStorageDevice3_itWorks() {
		return storageDevice3_itWorks;
	}

	public void setStorageDevice3_itWorks(Boolean storageDevice3_itWorks) {
		this.storageDevice3_itWorks = storageDevice3_itWorks;
	}

	public Double getStorageDevice3_sizeInGB() {
		return storageDevice3_sizeInGB;
	}

	public void setStorageDevice3_sizeInGB(Double storageDevice3_sizeInGB) {
		this.storageDevice3_sizeInGB = storageDevice3_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice3_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice3_architecture);
	}

	public void setStorageDevice3_architecture(StorageDeviceArchitecture storageDevice3_architecture) {
		this.storageDevice3_architecture = storageDevice3_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice3_type() {
		return StorageDeviceType.toEnum(storageDevice3_type);
	}

	public void setStorageDevice3_type(StorageDeviceType storageDevice3_type) {
		this.storageDevice3_type = storageDevice3_type.getCod();
	}

	public Long getStorageDevice4_id() {
		return storageDevice4_id;
	}

	public void setStorageDevice4_id(Long storageDevice4_id) {
		this.storageDevice4_id = storageDevice4_id;
	}

	public String getStorageDevice4_manufacturer() {
		return storageDevice4_manufacturer;
	}

	public void setStorageDevice4_manufacturer(String storageDevice4_manufacturer) {
		this.storageDevice4_manufacturer = storageDevice4_manufacturer;
	}

	public String getStorageDevice4_model() {
		return storageDevice4_model;
	}

	public void setStorageDevice4_model(String storageDevice4_model) {
		this.storageDevice4_model = storageDevice4_model;
	}

	public String getStorageDevice4_description() {
		return storageDevice4_description;
	}

	public void setStorageDevice4_description(String storageDevice4_description) {
		this.storageDevice4_description = storageDevice4_description;
	}

	public Boolean getStorageDevice4_itWorks() {
		return storageDevice4_itWorks;
	}

	public void setStorageDevice4_itWorks(Boolean storageDevice4_itWorks) {
		this.storageDevice4_itWorks = storageDevice4_itWorks;
	}

	public Double getStorageDevice4_sizeInGB() {
		return storageDevice4_sizeInGB;
	}

	public void setStorageDevice4_sizeInGB(Double storageDevice4_sizeInGB) {
		this.storageDevice4_sizeInGB = storageDevice4_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice4_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice4_architecture);
	}

	public void setStorageDevice4_architecture(StorageDeviceArchitecture storageDevice4_architecture) {
		this.storageDevice4_architecture = storageDevice4_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice4_type() {
		return StorageDeviceType.toEnum(storageDevice4_type);
	}

	public void setStorageDevice4_type(StorageDeviceType storageDevice4_type) {
		this.storageDevice4_type = storageDevice4_type.getCod();
	}

	public Long getStorageDevice5_id() {
		return storageDevice5_id;
	}

	public void setStorageDevice5_id(Long storageDevice5_id) {
		this.storageDevice5_id = storageDevice5_id;
	}

	public String getStorageDevice5_manufacturer() {
		return storageDevice5_manufacturer;
	}

	public void setStorageDevice5_manufacturer(String storageDevice5_manufacturer) {
		this.storageDevice5_manufacturer = storageDevice5_manufacturer;
	}

	public String getStorageDevice5_model() {
		return storageDevice5_model;
	}

	public void setStorageDevice5_model(String storageDevice5_model) {
		this.storageDevice5_model = storageDevice5_model;
	}

	public String getStorageDevice5_description() {
		return storageDevice5_description;
	}

	public void setStorageDevice5_description(String storageDevice5_description) {
		this.storageDevice5_description = storageDevice5_description;
	}

	public Boolean getStorageDevice5_itWorks() {
		return storageDevice5_itWorks;
	}

	public void setStorageDevice5_itWorks(Boolean storageDevice5_itWorks) {
		this.storageDevice5_itWorks = storageDevice5_itWorks;
	}

	public Double getStorageDevice5_sizeInGB() {
		return storageDevice5_sizeInGB;
	}

	public void setStorageDevice5_sizeInGB(Double storageDevice5_sizeInGB) {
		this.storageDevice5_sizeInGB = storageDevice5_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice5_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice5_architecture);
	}

	public void setStorageDevice5_architecture(StorageDeviceArchitecture storageDevice5_architecture) {
		this.storageDevice5_architecture = storageDevice5_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice5_type() {
		return StorageDeviceType.toEnum(storageDevice5_type);
	}

	public void setStorageDevice5_type(StorageDeviceType storageDevice5_type) {
		this.storageDevice5_type = storageDevice5_type.getCod();
	}

	public Long getStorageDevice6_id() {
		return storageDevice6_id;
	}

	public void setStorageDevice6_id(Long storageDevice6_id) {
		this.storageDevice6_id = storageDevice6_id;
	}

	public String getStorageDevice6_manufacturer() {
		return storageDevice6_manufacturer;
	}

	public void setStorageDevice6_manufacturer(String storageDevice6_manufacturer) {
		this.storageDevice6_manufacturer = storageDevice6_manufacturer;
	}

	public String getStorageDevice6_model() {
		return storageDevice6_model;
	}

	public void setStorageDevice6_model(String storageDevice6_model) {
		this.storageDevice6_model = storageDevice6_model;
	}

	public String getStorageDevice6_description() {
		return storageDevice6_description;
	}

	public void setStorageDevice6_description(String storageDevice6_description) {
		this.storageDevice6_description = storageDevice6_description;
	}

	public Boolean getStorageDevice6_itWorks() {
		return storageDevice6_itWorks;
	}

	public void setStorageDevice6_itWorks(Boolean storageDevice6_itWorks) {
		this.storageDevice6_itWorks = storageDevice6_itWorks;
	}

	public Double getStorageDevice6_sizeInGB() {
		return storageDevice6_sizeInGB;
	}

	public void setStorageDevice6_sizeInGB(Double storageDevice6_sizeInGB) {
		this.storageDevice6_sizeInGB = storageDevice6_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice6_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice6_architecture);
	}

	public void setStorageDevice6_architecture(StorageDeviceArchitecture storageDevice6_architecture) {
		this.storageDevice6_architecture = storageDevice6_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice6_type() {
		return StorageDeviceType.toEnum(storageDevice6_type);
	}

	public void setStorageDevice6_type(StorageDeviceType storageDevice6_type) {
		this.storageDevice6_type = storageDevice6_type.getCod();
	}

	public Long getStorageDevice7_id() {
		return storageDevice7_id;
	}

	public void setStorageDevice7_id(Long storageDevice7_id) {
		this.storageDevice7_id = storageDevice7_id;
	}

	public String getStorageDevice7_manufacturer() {
		return storageDevice7_manufacturer;
	}

	public void setStorageDevice7_manufacturer(String storageDevice7_manufacturer) {
		this.storageDevice7_manufacturer = storageDevice7_manufacturer;
	}

	public String getStorageDevice7_model() {
		return storageDevice7_model;
	}

	public void setStorageDevice7_model(String storageDevice7_model) {
		this.storageDevice7_model = storageDevice7_model;
	}

	public String getStorageDevice7_description() {
		return storageDevice7_description;
	}

	public void setStorageDevice7_description(String storageDevice7_description) {
		this.storageDevice7_description = storageDevice7_description;
	}

	public Boolean getStorageDevice7_itWorks() {
		return storageDevice7_itWorks;
	}

	public void setStorageDevice7_itWorks(Boolean storageDevice7_itWorks) {
		this.storageDevice7_itWorks = storageDevice7_itWorks;
	}

	public Double getStorageDevice7_sizeInGB() {
		return storageDevice7_sizeInGB;
	}

	public void setStorageDevice7_sizeInGB(Double storageDevice7_sizeInGB) {
		this.storageDevice7_sizeInGB = storageDevice7_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice7_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice7_architecture);
	}

	public void setStorageDevice7_architecture(StorageDeviceArchitecture storageDevice7_architecture) {
		this.storageDevice7_architecture = storageDevice7_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice7_type() {
		return StorageDeviceType.toEnum(storageDevice7_type);
	}

	public void setStorageDevice7_type(StorageDeviceType storageDevice7_type) {
		this.storageDevice7_type = storageDevice7_type.getCod();
	}

	public Long getStorageDevice8_id() {
		return storageDevice8_id;
	}

	public void setStorageDevice8_id(Long storageDevice8_id) {
		this.storageDevice8_id = storageDevice8_id;
	}

	public String getStorageDevice8_manufacturer() {
		return storageDevice8_manufacturer;
	}

	public void setStorageDevice8_manufacturer(String storageDevice8_manufacturer) {
		this.storageDevice8_manufacturer = storageDevice8_manufacturer;
	}

	public String getStorageDevice8_model() {
		return storageDevice8_model;
	}

	public void setStorageDevice8_model(String storageDevice8_model) {
		this.storageDevice8_model = storageDevice8_model;
	}

	public String getStorageDevice8_description() {
		return storageDevice8_description;
	}

	public void setStorageDevice8_description(String storageDevice8_description) {
		this.storageDevice8_description = storageDevice8_description;
	}

	public Boolean getStorageDevice8_itWorks() {
		return storageDevice8_itWorks;
	}

	public void setStorageDevice8_itWorks(Boolean storageDevice8_itWorks) {
		this.storageDevice8_itWorks = storageDevice8_itWorks;
	}

	public Double getStorageDevice8_sizeInGB() {
		return storageDevice8_sizeInGB;
	}

	public void setStorageDevice8_sizeInGB(Double storageDevice8_sizeInGB) {
		this.storageDevice8_sizeInGB = storageDevice8_sizeInGB;
	}

	public StorageDeviceArchitecture getStorageDevice8_architecture() {
		return StorageDeviceArchitecture.toEnum(storageDevice8_architecture);
	}

	public void setStorageDevice8_architecture(StorageDeviceArchitecture storageDevice8_architecture) {
		this.storageDevice8_architecture = storageDevice8_architecture.getCod();
	}

	public StorageDeviceType getStorageDevice8_type() {
		return StorageDeviceType.toEnum(storageDevice8_type);
	}

	public void setStorageDevice8_type(StorageDeviceType storageDevice8_type) {
		this.storageDevice8_type = storageDevice8_type.getCod();
	}



}

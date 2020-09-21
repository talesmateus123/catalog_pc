/** 
 * This is the class "Computer", extended by the abstract class "Machine". Which will be to represent a computer.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.ComputerType;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.OperatingSystem;

@Entity
public class Computer extends Equipment{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String macAddress;
	private String hostName;
	private boolean online = false;
	private String motherBoardName;
	private boolean hasCdBurner = true;
	private String cabinetModel;
	private Integer operatingSystem = 0;
	private Integer operatingSystemArchitecture = 0;
	@NotNull
	private Integer computerType = 0;
	@NotNull
	private boolean onTheDomain = false;
	private boolean personalComputer = false;
	private String teamViewerId;
	private String teamViewerPass;
	private Double totalRamMemory = 0.0;
	private Double totalStorageMemory = 0.0;
	@OneToMany(mappedBy = "computer", fetch = FetchType.EAGER)
	private List<RamMemory> ramMemories = new ArrayList<>();
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "computer")
	private List<StorageDevice> storageDevices = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "computer_computer_user",
		joinColumns = @JoinColumn(name = "computer_id"),
		inverseJoinColumns =  @JoinColumn(name = "computer_user_id")
			)
	private List<ComputerUser> computerUsers = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "monitor_id")
	private Monitor monitor;
	@OneToOne
	@JoinColumn(name = "processor_id")
	private Processor processor;
	
	public Computer() {
		super();
		this.setEquipmentType(EquipmentType.COMPUTER);
	}

	public Computer(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, Date deletedDate, String manufacturer, 
			String model, String description, Sector sector, String ipAddress, String macAddress,
			String hostName, String motherBoardName, Processor processor, Boolean hasCdBurner, String cabinetModel, 
			OperatingSystem operatingSystem, ArchitectureType operatingSystemArchitecture, ComputerType computerType, 
			boolean onTheDomain, boolean personalComputer, String teamViewerId, String teamViewerPass, Double totalRamMemory, Double totalStorageMemory, Monitor monitor) {
		super(id, patrimonyId, createdDate, lastModifiedDate, deletedDate, EquipmentType.COMPUTER, manufacturer, model, description, sector);
		if(ipAddress != null)
			this.ipAddress = (!ipAddress.isEmpty()) ? ipAddress : "0.0.0.0";
		else
			this.ipAddress = "0.0.0.0";
		this.macAddress = macAddress;
		if(hostName != null)
			this.hostName = (!hostName.isEmpty()) ? hostName : generateHostName();
		else
			this.hostName = generateHostName();
		this.computerType = (computerType != null) ? computerType.getCod() : null;;
		this.teamViewerId = teamViewerId;
		this.teamViewerPass = teamViewerPass;
		this.personalComputer = personalComputer;
		if (!this.personalComputer) {
			this.motherBoardName = motherBoardName;
			this.processor = processor;
			this.hasCdBurner = hasCdBurner;
			this.cabinetModel = cabinetModel;
			this.operatingSystem = (operatingSystem != null) ? operatingSystem.getCod() : null;
			this.operatingSystemArchitecture = (operatingSystemArchitecture != null) ? operatingSystemArchitecture.getCod() : null;
			this.onTheDomain = onTheDomain;
			this.totalRamMemory = totalRamMemory;
			this.totalStorageMemory = totalStorageMemory;
			this.monitor = monitor;
		}
		else {
			this.patrimonyId = null;
			this.manufacturer = null;
			this.model = null;
		}
			
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String macAddress) {
		this.macAddress = macAddress;
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
	
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getMotherBoardName() {
		return motherBoardName;
	}

	public void setMotherBoardName(String motherBoardName) {
		this.motherBoardName = motherBoardName;
	}

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
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

	public void setIsOnTheDomain(boolean onTheDomain) {
		this.onTheDomain = onTheDomain;
	}
	
	public boolean isPersonalComputer() {
		return personalComputer;
	}

	public void setIsPersonalComputer(boolean personalComputer) {
		this.personalComputer = personalComputer;
	}

	public String getTeamViewerId() {
		return teamViewerId;
	}

	public void setTeamViewerId(String teamViewerId) {
		this.teamViewerId = teamViewerId;
	}

	public String getTeamViewerPass() {
		return teamViewerPass;
	}

	public void setTeamViewerPass(String teamViewerPass) {
		this.teamViewerPass = teamViewerPass;
	}

	public Double getTotalStorageMemory() {
		return totalStorageMemory;
	}
	
	public void setTotalStorageMemory(Double totalStorageMemory) {
		this.totalStorageMemory = totalStorageMemory;
	}
	
	public Double getTotalRamMemory() {
		return totalRamMemory;
	}
	
	public void setTotalRamMemory(Double totalRamMemory) {
		this.totalRamMemory = totalRamMemory;
	}

	public List<RamMemory> getRamMemories() {
		return ramMemories;
	}

	public void setRamMemories(List<RamMemory> ramMemories) {
		this.ramMemories = ramMemories;
	}
	
	/**
	 * Add a new ram memory object to this list of ram memory.
	 * @param ramMemory RamMemory
	 * @return void
	 */	
	public void addRamMemory(RamMemory ramMemory) {
		if(this.ramMemories.contains(ramMemory) && ramMemory.getId() != null)
			return;
		this.ramMemories.add(ramMemory);
		//this.generateTotalRamMemory();
	}

	public List<StorageDevice> getStorageDevices() {
		return storageDevices;
	}

	public void setStorageDevices(List<StorageDevice> storageDevices) {
		this.storageDevices = storageDevices;
	}
	
	/**
	 * Add a new storage device object to this list of storage device.
	 * @param storageDevice StorageDevice
	 * @return void
	 */
	public void addStorageDevice(StorageDevice storageDevice) {
		if(this.storageDevices.contains(storageDevice) && storageDevice.getId() != null)
			return;
		this.storageDevices.add(storageDevice);
		//this.generateTotalStorageMemory();
	}

	public List<ComputerUser> getComputerUsers() {
		return computerUsers;
	}

	public void setComputerUsers(List<ComputerUser> computerUsers) {
		this.computerUsers = computerUsers;
	}
	
	/**
	 * Add a new computer user object to this list of computer users.
	 * @param computerUser ComputerUser
	 * @return void
	 */
	public void addComputerUser(ComputerUser computerUser) {
		this.computerUsers.add(computerUser);
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	
	public void generateTotalRamMemory() {
		this.totalRamMemory = 0.0;
		if (this.ramMemories != null || this.ramMemories.isEmpty()) {
			for (RamMemory ramMemory : this.ramMemories)
				this.totalRamMemory = this.totalRamMemory + ramMemory.getSizeInGB();
		}
	}

	public void generateTotalStorageMemory() {
		this.totalStorageMemory = 0.0;
		if (this.storageDevices != null || !this.storageDevices.isEmpty()) {
			for (StorageDevice storageDevice : this.storageDevices)
				this.totalStorageMemory = this.totalStorageMemory + storageDevice.getSizeInGB();
		}
	}
	
	public String generateHostName(){
		return this.patrimonyId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Computer [");
		builder.append(id);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", lastModifiedDate=");
		builder.append(lastModifiedDate);
		builder.append(", equipmentType=");
		builder.append(getEquipmentType().getDescription());
		builder.append(", manufacturer=");
		builder.append(manufacturer);
		builder.append(", model=");
		builder.append(model);
		builder.append(", description=");
		builder.append(description);
		builder.append(", patrimonyId=");
		builder.append(getPatrimonyId());
		builder.append(", sector=");
		builder.append(getSector().getName());
		builder.append(", ipAddress=");
		builder.append(ipAddress);
		builder.append(", hostName=");
		builder.append(hostName);
		builder.append(", motherBoardName=");
		builder.append(motherBoardName);
		builder.append(", hasCdBurner=");
		if(hasCdBurner)
			builder.append("Yes");
		else
			builder.append("No");
		builder.append(", cabinetModel=");
		builder.append(cabinetModel);
		builder.append(", operatingSystem=");		
		builder.append((getOperatingSystem() != null) ? getOperatingSystem().getDescription() : "Undefined");
		builder.append(", operatingSystemArchitecture=");
		builder.append((getOperatingSystemArchitecture() != null) ? getOperatingSystemArchitecture().getDescription() : "Undefined");
		builder.append(", onTheDomain=");
		if(onTheDomain)
			builder.append("Yes");
		else
			builder.append("No");
		builder.append(", ramMemories=");
		for(RamMemory object : getRamMemories())
			builder.append(object.getManufacturer() + " - " + object.getModel());
		builder.append(", totalRamMemory=");
		builder.append(getTotalRamMemory());
		builder.append(", storageDevices=");
		for(StorageDevice object : getStorageDevices())
			builder.append(object.getManufacturer() + " - " + object.getModel());
		builder.append(", totalStorageMemory=");
		builder.append(getTotalStorageMemory());
		builder.append(", computerUsers=");
		for(ComputerUser object : getComputerUsers())
			builder.append(object.getName() + " " + object.getLastName());
		builder.append(", monitor=");
		
		builder.append((monitor != null) ? monitor.getManufacturer() : "Undefined");
		builder.append(" - ");
		builder.append((monitor != null) ? monitor.getModel() : "Undefined");
		builder.append(", processor=");
		builder.append((processor != null) ? processor.getProcessorName() : "Undefined");
		builder.append("]");
		return builder.toString();
	}

	
	
}


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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.OperatingSystem;
import com.pml.domain.enums.Sector;

@Entity
public class Computer extends Machine{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String hostName;
	private String motherBoardName;
	private Boolean hasCdBurner;
	private String cabinetModel;
	@NotNull
	private Integer operatingSystem;
	@NotNull
	private Integer operatingSystemArchitecture;
	@NotNull
	private boolean onTheDomain;	

	@OneToMany(mappedBy = "computer")
	private List<RamMemory> ramMemories = new ArrayList<>();
	private Double totalRamMemory;
	@OneToMany(mappedBy = "computer")
	private List<StorageDevice> storageDevices = new ArrayList<>();
	private Double totalStorageMemory;	
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
		this.totalRamMemory = 0.0;
		this.totalStorageMemory = 0.0;
	}

	public Computer(Long id, String patrimonyId, Date createdDate, Date modifiedDate, String manufacturer, 
			String model, String description, Sector sector, boolean itWorks, String ipAddress, 
			String hostName, String motherBoardName, Processor processor, Boolean hasCdBurner, String cabinetModel, 
			OperatingSystem operatingSystem, ArchitectureType operatingSystemArchitecture, boolean onTheDomain, 
			Monitor monitor) {
		super(id, patrimonyId, createdDate, modifiedDate, EquipmentType.COMPUTER, manufacturer, model, description, sector, itWorks);
		this.ipAddress = (ipAddress != null) ? ipAddress : "0.0.0.0";
		this.hostName = (hostName != null) ? hostName : generateHostName();
		this.hostName = hostName;
		this.motherBoardName = (motherBoardName != null) ? motherBoardName : "N/A";
		this.processor = processor;
		this.hasCdBurner = hasCdBurner;
		this.cabinetModel = (cabinetModel != null) ? cabinetModel : "N/A";
		this.operatingSystem = operatingSystem.getCod();
		this.operatingSystemArchitecture = operatingSystemArchitecture.getCod();
		this.onTheDomain = onTheDomain;
		this.monitor = monitor;
		this.totalRamMemory = 0.0;
		this.totalStorageMemory = 0.0;
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

	public boolean isOnTheDomain() {
		return onTheDomain;
	}

	public void setIsOnTheDomain(boolean onTheDomain) {
		this.onTheDomain = onTheDomain;
	}

	public List<RamMemory> getRamMemories() {
		return ramMemories;
	}

	public void setRamMemories(List<RamMemory> ramMemories) {
		this.ramMemories = ramMemories;
		this.calculateTotalRamMemories();
	}
	
	/**
	 * Add a new ram memory object to this list of ram memory.
	 * @param ramMemory RamMemory
	 * @return void
	 */	
	public void addRamMemory(RamMemory ramMemory) {
		this.ramMemories.add(ramMemory);
		this.totalRamMemory = this.totalRamMemory + ramMemory.getSizeInMB();
	}

	public List<StorageDevice> getStorageDevices() {
		return storageDevices;
	}

	public void setStorageDevices(List<StorageDevice> storageDevices) {
		this.storageDevices = storageDevices;
		this.calculateTotalStorageDeviceMemories();		
	}
	
	/**
	 * Add a new storage device object to this list of storage device.
	 * @param storageDevice StorageDevice
	 * @return void
	 */
	public void addStorageDevice(StorageDevice storageDevice) {
		this.storageDevices.add(storageDevice);
		this.totalStorageMemory = this.totalStorageMemory + storageDevice.getSizeInMB();
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
	
	public Double getTotalStorageMemory() {
		return totalStorageMemory;
	}
	
	public Double getTotalRamMemory() {
		return totalRamMemory;
	}
	
	public String generateHostName(){
		if(patrimonyId != null)
			return "PML" + patrimonyId;
		return "N/A";		
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
		builder.append(getEquipmentType().toString());
		builder.append(", manufacturer=");
		builder.append(manufacturer);
		builder.append(", model=");
		builder.append(model);
		builder.append(", description=");
		builder.append(description);
		builder.append(", itWorks=");
		if(super.isItWorks())
			builder.append("Yes");
		else
			builder.append("No");
		builder.append(", patrimonyId=");
		builder.append(getPatrimonyId());
		builder.append(", sector=");
		builder.append(getSector().toString());
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
		builder.append(getOperatingSystem().toString());
		builder.append(", operatingSystemArchitecture=");
		builder.append(getOperatingSystemArchitecture().toString());
		builder.append(", onTheDomain=");
		if(onTheDomain)
			builder.append("Yes");
		else
			builder.append("No");
		builder.append(", ramMemories=");
		for(RamMemory object : getRamMemories())
			builder.append(object.getManufacturer() + " - " + object.getModel());
		builder.append(", totalRamMemory=");
		builder.append(totalRamMemory);
		builder.append(", storageDevices=");
		for(StorageDevice object : getStorageDevices())
			builder.append(object.getManufacturer() + " - " + object.getModel());
		builder.append(", totalStorageMemory=");
		builder.append(totalStorageMemory);
		builder.append(", computerUsers=");
		for(ComputerUser object : getComputerUsers())
			builder.append(object.getName() + " " + object.getLastName());
		builder.append(", monitor=");
		builder.append(monitor.getManufacturer());
		builder.append(monitor.getModel());
		builder.append(", processor=");
		builder.append(processor.getProcessorName());
		builder.append("]");
		return builder.toString();
	}

	private void calculateTotalRamMemories() {
		if (this.ramMemories.isEmpty()) {
			Double totalMemory = 0.0;
			for (RamMemory ramMemory : this.ramMemories)
				totalMemory = totalMemory + ramMemory.getSizeInMB();
			this.totalRamMemory = totalMemory;
		}
	}
	
	private void calculateTotalStorageDeviceMemories() {
		if (this.storageDevices.isEmpty()) {
			Double totalMemory = 0.0;
			for (StorageDevice storageDevice : this.storageDevices)
				totalMemory = totalMemory + storageDevice.getSizeInMB();
			this.totalStorageMemory = totalMemory;
		}
	}
	
	
}


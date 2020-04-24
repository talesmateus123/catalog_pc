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
import com.pml.domain.enums.OperatingSystemType;
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
	private List<RamMemory> ramMemories;
	private Double totalRamMemory;
	@OneToMany(mappedBy = "computer")
	private List<StorageDevice> storageDevices;
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
		calculateTotalMemories();
	}

	public Computer(Long id, String patrimonyId, Date createdDate, Date modifiedDate, String manufacturer, 
			String model, String description, Sector sector, boolean itWorks, String ipAddress, 
			String hostName, String motherBoardName, Processor processor, Boolean hasCdBurner, String cabinetModel, 
			OperatingSystemType operatingSystem, ArchitectureType operatingSystemArchitecture, boolean onTheDomain, 
			Monitor monitor) {
		super(id, patrimonyId, createdDate, modifiedDate, EquipmentType.COMPUTER, manufacturer, model, description, sector, itWorks);
		this.ipAddress = ipAddress;
		this.hostName = hostName;
		this.motherBoardName = motherBoardName;
		this.processor = processor;
		this.hasCdBurner = hasCdBurner;
		this.cabinetModel = cabinetModel;
		this.operatingSystem = operatingSystem.getCod();
		this.operatingSystemArchitecture = operatingSystemArchitecture.getCod();
		this.onTheDomain = onTheDomain;
		this.monitor = monitor;
		calculateTotalMemories();
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

	public void setIsOnTheDomain(boolean onTheDomain) {
		this.onTheDomain = onTheDomain;
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
		this.ramMemories.add(ramMemory);
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
		this.storageDevices.add(storageDevice);
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
	
	private void calculateTotalMemories() {
		Double totalMemory;
		if (this.storageDevices != null) {
			totalMemory = 0.0;
			for (StorageDevice storageDevice : this.storageDevices)
				totalMemory = totalMemory + storageDevice.getSizeInMB();
			this.totalStorageMemory = totalMemory;
		}
		else
			this.totalStorageMemory = 0.0;
		if (this.ramMemories != null) {
			totalMemory = 0.0;
			for (RamMemory ramMemory : this.ramMemories)
				totalMemory = totalMemory + ramMemory.getSizeInMB();
			this.totalRamMemory = totalMemory;
		}
		else
			this.totalRamMemory = 0.0;
	}
	
}


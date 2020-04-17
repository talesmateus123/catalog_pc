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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.HardDiskType;
import com.pml.domain.enums.MachineType;
import com.pml.domain.enums.OperatingSystemType;
import com.pml.domain.enums.RamMemoryType;

@Entity
public class Computer extends Machine{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String motherBoardName;
	@javax.validation.constraints.NotNull
	private Integer memoryType;
	private Double memorySize;
	@javax.validation.constraints.NotNull
	private Integer hdType;
	private Double hdSize;
	@NotEmpty
	private String processorModel;
	@javax.validation.constraints.NotNull
	private Integer processorArchitecture;
	private Boolean hasCdBurner;
	@NotEmpty
	private String cabinetModel;
	@javax.validation.constraints.NotNull
	private Integer operatingSystem;
	private Integer operatingSystemArchitecture;	
	@ManyToMany
	@JoinTable(name = "computer_computer_user",
		joinColumns = @JoinColumn(name = "computer_id"),
		inverseJoinColumns =  @JoinColumn(name = "computer_user_id")
			)
	private List<ComputerUser> computerUsers = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "computer_id")
	private Monitor monitor;
	
	
	public Computer() {
		super();
		this.setMachineType(MachineType.COMPUTER);
	}

	public Computer(@Size(max = 10) String patrimonyId, Date createdDate, Date modifiedDate, @NotEmpty @Size(max = 20)
			@Size(max = 100) String manufacturer, @NotEmpty @Size(max = 100) String model, String description,
			Integer location, String motherBoardName, @NotEmpty Integer memoryType, Double memorySize, @NotEmpty Integer hdType,
			Double hdSize, @NotEmpty String processorModel, @NotEmpty @Size(max = 10) Integer processorArchitecture,
			Boolean hasCdBurner, @NotEmpty String cabinetModel, @NotEmpty Integer operatingSystem,
			@Size(max = 10) Integer operatingSystemArchitecture, Monitor monitor) {
		super(patrimonyId, createdDate, modifiedDate,  MachineType.COMPUTER, manufacturer, model, description, location);
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
		this.monitor = monitor;
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

	public List<ComputerUser> getComputerUsers() {
		return computerUsers;
	}

	public void setComputerUsers(List<ComputerUser> computerUsers) {
		this.computerUsers = computerUsers;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	
	
	
}

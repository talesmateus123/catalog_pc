package com.pml.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PrinterNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String patrimonyId;
	private String manufacturer;
	private String model;
	@Size(max = 100, message = "{description.size}")
	private String description;
	@NotNull(message = "{not.null}")
	private Integer sectorId;
	@NotNull(message = "{not.null}")
	private Boolean itWorks;
	private String ipAddress;
	private String hostName;
	
	public PrinterNewDTO() {
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
	
	public Integer getSectorId() {
		return sectorId;
	}
	
	public void setSectorId(Integer sectorId) {
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

	
	
}

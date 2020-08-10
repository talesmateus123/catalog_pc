package com.pml.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pml.domain.NetworkDevice;
import com.pml.domain.enums.EquipmentType;

public class NetworkDeviceDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date createdDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date lastModifiedDate;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date deletedDate;
	private Integer equipmentType;
	private String manufacturer;
	private String model;
	private String description;
	private boolean itComposed;
	private String patrimonyId;
	private String sectorName;
	private String ipAddress;
	private String macAddress;
	private String hostName;
	private boolean online;
	
	public NetworkDeviceDTO() {
	}
	
	public NetworkDeviceDTO(NetworkDevice networkDevice) {
		this.id = networkDevice.getId();
		this.createdDate = networkDevice.getCreatedDate();
		this.lastModifiedDate = networkDevice.getLastModifiedDate();
		this.deletedDate = networkDevice.getDeletedDate();
		this.equipmentType = networkDevice.getEquipmentType().getCod();
		this.manufacturer = networkDevice.getManufacturer();
		this.model = networkDevice.getModel();
		this.description = networkDevice.getDescription();
		this.itComposed = networkDevice.isItComposed();
		this.patrimonyId = networkDevice.getPatrimonyId();
		this.sectorName = networkDevice.getSector().getName();
		this.ipAddress = networkDevice.getIpAddress();
		this.macAddress = networkDevice.getMacAddress();
		this.hostName = networkDevice.getHostName();
		this.online = networkDevice.isOnline();
		this.itComposed = networkDevice.isItComposed();
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

	public String getSectorName() {
		return sectorName;
	}
	
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
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
	
	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
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

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	

	
}

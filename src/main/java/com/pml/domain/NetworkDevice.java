/** 
 * This is the class "NetworkDevice", extended by the abstract class "Machine". Which will be to represent a computer.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.pml.domain.enums.EquipmentType;

@Entity
public class NetworkDevice extends Equipment{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String macAddress;
	private String hostName;
	private boolean online;
	
	public NetworkDevice() {
		super();
		this.setEquipmentType(EquipmentType.NETWORK_DEVICE);
	}

	public NetworkDevice(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, Date deletedDate, String manufacturer, 
			String model, String description, Sector sector, String ipAddress, String macAddress,
			String hostName) {
		super(id, patrimonyId, createdDate, lastModifiedDate, deletedDate, EquipmentType.NETWORK_DEVICE, manufacturer, model, description, sector);
		if(ipAddress != null)
			this.ipAddress = (!ipAddress.isEmpty()) ? ipAddress : "0.0.0.0";
		else
			this.ipAddress = "0.0.0.0";
		this.macAddress = macAddress;
		this.hostName = hostName;
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
	
	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public String generateHostName(){
		return this.sector.getName().replaceAll("\\s+", "").toUpperCase();
	}
	
	
	
}


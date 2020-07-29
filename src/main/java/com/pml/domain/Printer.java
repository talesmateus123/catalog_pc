/** 
 * This is the class "Computer", extended by the abstract class "Machine". Which will be to represent a computer.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.pml.domain.enums.EquipmentType;

@Entity
public class Printer extends Equipment{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String macAddress;
	private String hostName;
	
	public Printer() {
		super();
		this.setEquipmentType(EquipmentType.PRINTER);
	}

	public Printer(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, String manufacturer, 
			String model, String description, Sector sector, Boolean itWorks, String ipAddress, String macAddress,
			String hostName) {
		super(id, patrimonyId, createdDate, lastModifiedDate, EquipmentType.PRINTER, manufacturer, model, description, sector, itWorks);
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
	
	public String generateHostName(){
		return this.sector.getName().replaceAll("\\s+", "").toUpperCase();
	}
	
	
	
}


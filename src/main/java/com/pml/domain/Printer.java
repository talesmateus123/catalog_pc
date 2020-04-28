/** 
 * This is the class "Computer", extended by the abstract class "Machine". Which will be to represent a computer.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.Sector;

@Entity
public class Printer extends Machine{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String hostName;
	
	public Printer() {
		super();
		this.setEquipmentType(EquipmentType.PRINTER);
	}

	public Printer(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, String manufacturer, 
			String model, String description, Sector sector, boolean itWorks, String ipAddress, 
			String hostName) {
		super(id, patrimonyId, createdDate, lastModifiedDate, EquipmentType.PRINTER, manufacturer, model, description, sector, itWorks);
		this.ipAddress = (ipAddress != null) ? ipAddress : "0.0.0.0";
		this.hostName = (hostName != null) ? hostName : generateHostName();
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
	
	public String generateHostName(){
		if(patrimonyId != null)
			return "Brother " + getSector().getDescription();
		return "N/A";		
	}
	
	
}


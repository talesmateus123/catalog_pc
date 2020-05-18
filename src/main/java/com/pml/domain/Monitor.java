/** 
 * This is the class "Monitor", extended by the abstract class "Machine". Which will be to represent a monitor.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pml.domain.enums.EquipmentType;

@Entity
public class Monitor extends Equipment{
	private static final long serialVersionUID = 1L;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "monitor")
	@JsonIgnore
	private Computer computer;

	public Monitor() {
		super();
		this.setEquipmentType(EquipmentType.MONITOR);
	}

	public Monitor(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, String manufacturer, 
			String model, String description, Sector sector, boolean itWorks, Computer computer) {
		super(id, patrimonyId, createdDate, lastModifiedDate, EquipmentType.MONITOR, manufacturer, model, description, sector, itWorks);
		this.computer = computer;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	
	
}

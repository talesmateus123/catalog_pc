/** 
 * This is the abstract class "Machine", which it will be extended by the class "Computer" and class "Monitor".
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.Sector;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Machine extends Equipment {
	private static final long serialVersionUID = 1L;
	private String patrimonyId = "";
	private String manufacturer;
	private String model;
	private String description;
	private Integer sector;
	
	public Machine() {
		super();
		this.setItComposed(true);
	}

	public Machine(String patrimonyId, Date createdDate, Date modifiedDate, EquipmentType equipmentType, 
			String manufacturer, String model, String description, Integer sector, boolean itWorks) {
		super(createdDate, modifiedDate, equipmentType, manufacturer, model, description, itWorks, true);
		this.patrimonyId = patrimonyId;
		this.manufacturer = manufacturer;
		this.model = model;
		this.description = description;
		this.sector = (sector == null) ? null : sector;
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
	
	public Sector getSector() {
		return Sector.toEnum(sector);
	}
	
	public void setSector(Sector location) {
		this.sector = location.getCod();
	}

	
}

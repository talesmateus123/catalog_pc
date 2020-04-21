/** 
 * This is the abstract class "Machine", which it will be extended by the class "Computer" and class "Monitor".
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.Sector;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Machine extends Equipment {
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Size(min = 4, max = 10)
	@Column(unique = true)
	private String patrimonyId = "";
	@NotEmpty
	private String manufacturer;
	@NotEmpty
	private String model;
	private String description;
	@NotNull
	private Integer sector;
	
	public Machine() {
		super();
		this.setItComposed(true);
	}

	public Machine(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, EquipmentType equipmentType, 
			String manufacturer, String model, String description, Integer sector, boolean itWorks) {
		super(id, createdDate, lastModifiedDate, equipmentType, manufacturer, model, description, itWorks, true);
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

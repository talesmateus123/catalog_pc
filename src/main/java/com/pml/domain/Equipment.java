/** 
 * This is the abstract class "Machine", which it will be extended by the class "Computer" and class "Monitor".
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pml.domain.enums.EquipmentType;
import com.pml.domain.enums.Sector;

@Entity
public abstract class Equipment extends Electronic {
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Size(min = 4, max = 10)
	@Column(unique = true)
	protected String patrimonyId = "";
	@NotNull
	protected Integer sector;
	
	public Equipment() {
		super();
		this.setItComposed(true);
	}

	public Equipment(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, EquipmentType equipmentType, 
			String manufacturer, String model, String description, Sector sector, boolean itWorks) {
		super(id, createdDate, lastModifiedDate, equipmentType, manufacturer, model, description, itWorks, true);
		this.patrimonyId = (patrimonyId != null) ? patrimonyId : "N/A";
		this.sector = (sector == null) ? null : sector.getCod();
	}

	public String getPatrimonyId() {
		return patrimonyId;
	}
	
	public void setPatrimonyId(String patrimonyId) {
		this.patrimonyId = patrimonyId;
	}
	
	public Sector getSector() {
		return Sector.toEnum(sector);
	}
	
	public void setSector(Sector location) {
		this.sector = location.getCod();
	}


	
}

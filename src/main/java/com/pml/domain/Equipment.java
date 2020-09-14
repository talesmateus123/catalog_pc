/** 
 * This is the abstract class "Equipment", which it will be extended by the class "Computer" and class "Monitor".
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.pml.domain.enums.EquipmentType;

@Entity
public class Equipment extends Electronic {
	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	protected String patrimonyId = null;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "sector_id")
	protected Sector sector;
	
	public Equipment() {
		super();
		this.setItComposed(true);
	}

	public Equipment(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, Date deletedDate, EquipmentType equipmentType, 
			String manufacturer, String model, String description, Sector sector) {
		super(id, createdDate, lastModifiedDate, deletedDate, equipmentType, manufacturer, model, description, true);
		if(patrimonyId != null)
			this.patrimonyId = patrimonyId.replaceAll("\\s+", "").toUpperCase();
		else
			this.patrimonyId = patrimonyId;
		this.sector = sector;
	}

	public String getPatrimonyId() {
		return patrimonyId;
	}
	
	public void setPatrimonyId(String patrimonyId) {
		this.patrimonyId = patrimonyId;
	}
	
	public Sector getSector() {
		return sector;
	}
	
	public void setSector(Sector sector) {
		this.sector = sector;
	}


	
}

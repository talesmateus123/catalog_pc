package com.pml.dto;

import java.io.Serializable;

import com.pml.domain.Sector;

public class SectorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private boolean itInternal;
	
	public SectorDTO() {
	}
	
	public SectorDTO(Sector sector) {
		this.id = sector.getId();
		this.name = sector.getName();
		this.itInternal = sector.isItInternal();
	}
	
	public SectorDTO(Long id, String name, boolean itInternal) {
		this.id = id;
		this.name = name;
		this.itInternal = itInternal;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isItInternal() {
		return itInternal;
	}

	public void setItInternal(boolean itInternal) {
		this.itInternal = itInternal;
	}

	

}

package com.pml.dto;

import java.io.Serializable;

import com.pml.domain.Sector;

public class SectorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	public SectorDTO() {
	}
	
	public SectorDTO(Sector sector) {
		this.id = sector.getId();
		this.name = sector.getName();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	

}

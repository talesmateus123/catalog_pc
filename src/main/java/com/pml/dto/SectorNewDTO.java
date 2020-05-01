package com.pml.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SectorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 4, max = 50, message = "The text of this field must contain between 4 and 50 characters")
	private String name;
	private boolean itInternal;
	private List<Long> equipmentsId;
	private List<Long> computerUsersId;
	
	public SectorNewDTO() {
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
	
	public List<Long> getEquipmentsId() {
		return equipmentsId;
	}

	public void setEquipmentsId(List<Long> equipmentsId) {
		this.equipmentsId = equipmentsId;
	}

	public List<Long> getComputerUsersId() {
		return computerUsersId;
	}

	public void setComputerUsersId(List<Long> computerUsersId) {
		this.computerUsersId = computerUsersId;
	}
	
	

}

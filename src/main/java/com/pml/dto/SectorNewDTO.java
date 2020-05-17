package com.pml.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SectorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 3, max = 30, message = "The text of this field must contain between 3 and 30 characters")
	private String name;
	
	public SectorNewDTO() {
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

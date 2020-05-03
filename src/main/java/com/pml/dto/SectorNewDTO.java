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
	private boolean itInternal;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 3, max = 30, message = "The text of this field must contain between 3 and 30 characters")
	private String addressName;
	@NotEmpty(message = "This field is mandatory")
	@Size(min = 3, max = 30, message = "The text of this field must contain a maximum of 30 characters")
	private String addressStreet;
	@NotEmpty(message = "This field is mandatory")
	@Size(max = 10, message = "The text of this field must contain a maximum of 10 characters")
	private String addressNumber;
	private String addressNeighborhood;
	private String addressComplement;
	@NotEmpty(message = "This field is mandatory")
	@Size(max = 30, message = "The text of this field must contain a maximum of 30 characters")
	private String addressCity;
	private String addressTelephone;
	private List<Long> equipmentsId = new ArrayList<>();
	private List<Long> computerUsersId = new ArrayList<>();
	
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
	
	public boolean isItInternal() {
		return itInternal;
	}

	public void setItInternal(boolean itInternal) {
		this.itInternal = itInternal;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	
	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressNeighborhood() {
		return addressNeighborhood;
	}

	public void setAddressNeighborhood(String addressNeighborhood) {
		this.addressNeighborhood = addressNeighborhood;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}
	
	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	
	public String getAddressTelephone() {
		return addressTelephone;
	}

	public void setAddressTelephone(String addressTelephone) {
		this.addressTelephone = addressTelephone;
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

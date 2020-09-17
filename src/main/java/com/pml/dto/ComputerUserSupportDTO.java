package com.pml.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.pml.domain.Computer;

public class ComputerUserSupportDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String computerPatrimonyId;
	private String sectorName;
	private String teamViewerId;
	private String teamViewerPass;
	private List<String> computerUserNames;
	
	public ComputerUserSupportDTO() {
	}
	
	public ComputerUserSupportDTO(Computer computer) {
		this.id = computer.getId(); 
		this.computerPatrimonyId = computer.getPatrimonyId();
		this.sectorName = computer.getSector().getName();
		this.teamViewerId = computer.getTeamViewerId();
		this.teamViewerPass = computer.getTeamViewerPass();
		this.computerUserNames = computer.getComputerUsers().stream().map(computerUser -> computerUser.getName()).collect(Collectors.toList());	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getComputerPatrimonyId() {
		return computerPatrimonyId;
	}
	
	public void setComputerPatrimonyId(String computerPatrimonyId) {
		this.computerPatrimonyId = computerPatrimonyId;
	}
	
	public String getSectorName() {
		return sectorName;
	}
	
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getTeamViewerId() {
		return teamViewerId;
	}

	public void setTeamViewerId(String teamViewerId) {
		this.teamViewerId = teamViewerId;
	}

	public String getTeamViewerPass() {
		return teamViewerPass;
	}

	public void setTeamViewerPass(String teamViewerPass) {
		this.teamViewerPass = teamViewerPass;
	}

	public List<String> getComputerUserNames() {
		return computerUserNames;
	}

	public void setComputerUserNames(List<String> computerUserNames) {
		this.computerUserNames = computerUserNames;
	}
	

	
}

package com.pml.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SupportInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String teamViewerId;
	private String teamViewerPass;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "supportInfo")
	private Computer computer;
	
	
	
	public SupportInfo() {
	}

	public SupportInfo(Long id, String teamViewerId, String teamViewerPass, Computer computer) {
		this.id = id;
		this.teamViewerId = teamViewerId;
		this.teamViewerPass = teamViewerPass;
		this.computer = computer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	
	
}

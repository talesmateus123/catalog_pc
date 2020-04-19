package com.pml.dto;

import java.util.Date;

import com.pml.domain.Monitor;
import com.pml.domain.enums.EquipmentType;

public class MonitorDTO extends MachineDTO{
	private static final long serialVersionUID = 1L;
	
	public MonitorDTO() {	
		this.setItComposed(true);
		this.setEquipmentType(EquipmentType.MONITOR);
	}
	
	public MonitorDTO(Monitor monitor) {
		super(monitor.getId() , monitor.getPatrimonyId(), monitor.getCreatedDate(), monitor.getLastModifiedDate(), monitor.getEquipmentType().getCod(), 
				monitor.getManufacturer(), monitor.getModel(), monitor.getDescription(), monitor.getSector().getCod(), monitor.isItWorks(), true);
	}
	
	public MonitorDTO(Long id, String patrimonyId, Date createdDate, Date lastModifiedDate, Integer machineType,
			String manufacturer, String model, String description, Integer sector, boolean isItWorking) {
		super(id, patrimonyId, createdDate, lastModifiedDate, machineType, manufacturer, model, description, sector, isItWorking, true);
		
	}


}

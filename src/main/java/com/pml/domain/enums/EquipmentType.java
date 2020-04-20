package com.pml.domain.enums;

public enum EquipmentType {
	COMPUTER(0, "Computador"),
	MONITOR(1, "Monitor");
	
	private Integer cod;
	private String description;
	
	private EquipmentType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	/**
	 * Convert the cod of equipment to an EquipmentType object.
	 * @param cod Integer 
	 * @return EquipmentType
	 */
	public static EquipmentType toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (EquipmentType type : EquipmentType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

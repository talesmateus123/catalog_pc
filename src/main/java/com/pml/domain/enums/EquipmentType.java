package com.pml.domain.enums;

public enum EquipmentType {
	COMPUTER(0, "Computador"),
	PRINTER(1, "Printer"),
	MONITOR(2, "Monitor"),
	RAM_MEMORY(3, "RAM Memory"),
	PROCESSOR(4, "Processor"),
	STORAGE_DEVICE(5, "Storage device");
	
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

package com.pml.domain.enums;

public enum ComputerType {
	DESKTOP(0, "Desktop"),
	LAPTOP(1, "Notebook"),
	MOBILE(2, "Móvel");
	
	private Integer cod;
	private String description;
	
	private ComputerType(int cod, String description) {
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
	 * Convert the cod of computer type to an ComputerType object.
	 * @param cod Integer 
	 * @return ComputerType
	 */
	public static ComputerType toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (ComputerType type : ComputerType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

	
	
}

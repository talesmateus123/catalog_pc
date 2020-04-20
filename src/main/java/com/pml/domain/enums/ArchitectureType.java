package com.pml.domain.enums;

public enum ArchitectureType {
	AMD_64(0, "64 bits"),
	I_386(1, "32 bits");
	
	private Integer cod;
	private String description;
	
	private ArchitectureType(int cod, String description) {
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
	 * Convert the cod of architecture to an ArchitectureType object.
	 * @param cod Integer 
	 * @return ArchitectureType
	 */
	public static ArchitectureType toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (ArchitectureType type : ArchitectureType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

package com.pml.domain.enums;

public enum RamMemoryType {
	DDR1(0, "DDR1"),
	DDR2(1, "DDR2"),
	DDR3(2, "DDR3");
	
	private Integer cod;
	private String description;
	
	private RamMemoryType(int cod, String description) {
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
	 * Convert the cod of ram memory to a RamMemoryType object.
	 * @param cod Integer 
	 * @return RamMemoryType
	 */
	public static RamMemoryType toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (RamMemoryType type : RamMemoryType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

package com.pml.domain.enums;

public enum RamMemoryArchitecture {
	DDR1(0, "DDR1"),
	DDR2(1, "DDR2"),
	DDR3(1, "DDR3"),
	DDR4(2, "DDR4");
	
	private Integer cod;
	private String description;
	
	private RamMemoryArchitecture(int cod, String description) {
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
	public static RamMemoryArchitecture toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (RamMemoryArchitecture type : RamMemoryArchitecture.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

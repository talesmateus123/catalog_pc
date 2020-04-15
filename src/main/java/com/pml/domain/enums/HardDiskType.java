package com.pml.domain.enums;

public enum HardDiskType {
	SATA(0, "SATA"),
	IDE(1, "IDE");
	
	private Integer cod;
	private String description;
	
	private HardDiskType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static HardDiskType toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (HardDiskType type : HardDiskType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

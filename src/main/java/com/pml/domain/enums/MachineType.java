package com.pml.domain.enums;

public enum MachineType {
	COMPUTER(0, "Computador"),
	MONITOR(1, "Monitor");
	
	private Integer cod;
	private String description;
	
	private MachineType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static MachineType toEnum(Integer cod) {
		if (cod == null )
			return null;
		System.out.println("To aqui");
		
		for (MachineType type : MachineType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

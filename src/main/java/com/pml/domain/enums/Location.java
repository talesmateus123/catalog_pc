package com.pml.domain.enums;

public enum Location {
	ADMIN(0, "Administração"),
	RH(1, "Recursos Humanos");
	
	private Integer cod;
	private String description;
	
	private Location(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static Location toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (Location type : Location.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

package com.pml.domain.enums;

public enum Sector {
	ADMIN(0, "Administração"),
	RH(1, "Recursos Humanos");
	
	private Integer cod;
	private String description;
	
	private Sector(int cod, String description) {
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
	 * Convert the cod of sector to a Sector object
	 * @param cod Integer 
	 * @return Sector
	 */
	public static Sector toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (Sector type : Sector.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

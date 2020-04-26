package com.pml.domain.enums;

public enum UserProfile {
	ADMIN(0, "ROLE_ADMIN"),
	USER(1, "ROLE_USER");
	
	private Integer cod;
	private String description;
	
	private UserProfile(int cod, String description) {
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
	public static UserProfile toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (UserProfile type : UserProfile.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

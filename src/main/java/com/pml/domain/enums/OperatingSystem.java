package com.pml.domain.enums;

public enum OperatingSystem {
	NONE(0, "None"),
	WINDOWS_XP(1, "Windows XP"),
	WINDOWS_VISTA(2, "Windows Vista"),
	WINDOWS_7(3, "Windows 7"),
	WINDOWS_10(4, "Windows 10"),
	LINUX_DISTRO(5, "Distribuição linux");
	
	private Integer cod;
	private String description;
	
	private OperatingSystem(int cod, String description) {
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
	 * Convert the cod of operating system to an OperatingSystemType object.
	 * @param cod Integer 
	 * @return OperatingSystemType
	 */
	public static OperatingSystem toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (OperatingSystem type : OperatingSystem.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}
	
	

}

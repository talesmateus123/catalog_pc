package com.pml.domain.enums;

public enum OperatingSystemType {
	WINDOWS_XP(0, "Windows XP"),
	WINDOWS_VISTA(1, "Windows Vista"),
	WINDOWS_7(2, "Windows 7"),
	WINDOWS_10(3, "Windows 10"),
	LINUX_DISTRO(4, "Distribuição linux");
	
	private Integer cod;
	private String description;
	
	private OperatingSystemType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static OperatingSystemType toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (OperatingSystemType type : OperatingSystemType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

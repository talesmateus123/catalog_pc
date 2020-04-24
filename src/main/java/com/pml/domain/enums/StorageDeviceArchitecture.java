package com.pml.domain.enums;

public enum StorageDeviceArchitecture {
	SATA(0, "SATA"),
	IDE(1, "IDE");
	
	private Integer cod;
	private String description;
	
	private StorageDeviceArchitecture(int cod, String description) {
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
	 * Convert the cod of hard disk to a HardDiskType object.
	 * @param cod Integer 
	 * @return HardDiskType
	 */
	public static StorageDeviceArchitecture toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (StorageDeviceArchitecture type : StorageDeviceArchitecture.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}

}

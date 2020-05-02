package com.pml.domain.enums;

public enum StorageDeviceType {
	HD(0, "Hard Disk"),
	SSD(1, "Solid State Drive");
	
	private Integer cod;
	private String description;
	
	private StorageDeviceType(int cod, String description) {
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
	public static StorageDeviceType toEnum(Integer cod) {
		if (cod == null )
			return null;
		
		for (StorageDeviceType type : StorageDeviceType.values()) {
			if (cod == type.getCod()) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod + ".");
	}
	
	

}

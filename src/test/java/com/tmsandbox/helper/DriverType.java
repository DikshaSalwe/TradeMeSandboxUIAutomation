package com.tmsandbox.helper;

public enum DriverType {
	CHROME ("chrome"), 
	FIREFOX ("firefox"), 
	IE ( "ie"), 
	EDGE ("edge");
	
	private final String driverType;
	
	DriverType(String type) {
		this.driverType = type;
	}
	
	public String getDriverType() {
		return this.driverType;
	}
}

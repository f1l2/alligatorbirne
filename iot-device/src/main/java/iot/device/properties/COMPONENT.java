package iot.device.properties;

public enum COMPONENT {
	
	IDEV("idev", "IoTDevice"),
	
	CMGMT("cmgmt","ConfigurationManagement"),
	
	EPROC("eproc","EventProcessing");
	
	private final String value;
	
	private final String description;
	
	COMPONENT(final String type, final String description) {
		this.value = type;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
}

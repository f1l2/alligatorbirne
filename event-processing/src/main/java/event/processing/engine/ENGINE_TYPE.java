package event.processing.engine;

public enum ENGINE_TYPE {
	
	ESPER("esper", "Esper Engine");
	
	private final String value;
	
	private final String description;
	
	ENGINE_TYPE(final String type, final String description) {
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

package common.data.type;

public enum DOMAIN_TYPE {
	
	FIRST_FLOOR("ff", "First Floor"),
	
	SECOND_FLOOR("sf", "Second Floor");
	
	private final String value;
	
	private final String description;
	
	DOMAIN_TYPE (final String type, final String description) {
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

package common.data;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationModification extends DataModel {
	
	private long eventProcessingId;
	
	private String eventProcessingUrl;
	
	private Map<String, String> properties =new HashMap<String, String>();

	public long getEventProcessingId() {
		return eventProcessingId;
	}

	public void setEventProcessingId(long epId) {
		this.eventProcessingId = epId;
	}

	public String getEventProcessingUrl() {
		return eventProcessingUrl;
	}

	public void setEpUrl(String epUrl) {
		this.eventProcessingUrl = epUrl;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	
}

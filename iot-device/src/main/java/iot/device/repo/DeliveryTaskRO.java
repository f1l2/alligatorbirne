package iot.device.repo;

import java.util.HashMap;
import java.util.Map;


public class DeliveryTaskRO {
	
	private Long eventProcessingId;
	
	private String eventProcessingUrl;
	
	private Map<String, String> properties = new HashMap<String, String>();
	
	public Long getEventProcessingId() {
		return eventProcessingId;
	}

	public void setEventProcessingId(Long epId) {
		this.eventProcessingId = epId;
	}

	public String getEventProcessingUrl() {
		return eventProcessingUrl;
	}

	public void setEventProcessingUrl(String epUrl) {
		this.eventProcessingUrl = epUrl;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}

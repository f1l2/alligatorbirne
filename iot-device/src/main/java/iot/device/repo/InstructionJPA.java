package iot.device.repo;

import java.util.HashMap;
import java.util.Map;


public class InstructionJPA {
	
	private Long epId;
	
	private String epUrl;
	
	private Map<String, String> properties = new HashMap<String, String>();
	
	public Long getEpId() {
		return epId;
	}

	public void setEpId(Long epId) {
		this.epId = epId;
	}

	public String getEpUrl() {
		return epUrl;
	}

	public void setEpUrl(String epUrl) {
		this.epUrl = epUrl;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}

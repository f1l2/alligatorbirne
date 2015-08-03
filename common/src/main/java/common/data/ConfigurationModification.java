package common.data;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationModification extends DataModel {
	
	private long epId;
	
	private String epUrl;
	
	private Map<String, String> properties =new HashMap<String, String>();

	public long getEpId() {
		return epId;
	}

	public void setEpId(long epId) {
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

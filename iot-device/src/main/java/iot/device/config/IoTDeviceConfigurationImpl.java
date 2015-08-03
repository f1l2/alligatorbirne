package iot.device.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class IoTDeviceConfigurationImpl implements IoTDeviceConfiguration<String, String> {

	private static final Map<String, String> CONFIG = new HashMap<String, String>();
	
	@Override
	public String get(String key) {
		return CONFIG.get(key);
	}
	
	@Override
	public Boolean contains(String key) {
		return CONFIG.containsKey(key);
	}
	
	@Override
	public void put(String key, String value) {
			CONFIG.put(key, value);
	}
}

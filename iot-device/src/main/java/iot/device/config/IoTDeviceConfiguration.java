package iot.device.config;

public interface IoTDeviceConfiguration<KEY, VALUE> {

	public VALUE get(KEY key);

	public Boolean contains(KEY key);

	public void put(KEY key, VALUE value); 
}

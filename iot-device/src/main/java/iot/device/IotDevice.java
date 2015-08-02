package iot.device;

import iot.device.properties.COMPONENT;
import iot.device.properties.ConnectionProperties;
import iot.device.properties.IoTDeviceProperties;
import iot.device.status.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import common.data.DeviceInformation;
import common.data.Domain;
import common.data.MeasurementData;
import common.data.MeasurementPoint;
import common.data.type.DEVICEINFORMATION_TYPE;
import common.data.type.DOMAIN_TYPE;

@Controller
public class IotDevice {
	
	@Autowired
	private Status status;

	@Autowired
	private IoTDeviceProperties properties;
	

	public void setProperties(String ip, String port) {
		
		ConnectionProperties local = new ConnectionProperties(COMPONENT.IDEV, null, null, ip, port);
		properties.setConnectionPropertiesIoTDevice(local);
		
	}
	
	public void informCM() {

		MeasurementData mockedData = this.mockData(); 
	    
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(mockedData));
		
		RestTemplate restTemplate = new RestTemplate();

	    ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:5000/registrations", mockedData, String.class);
	    HttpStatus status = response.getStatusCode();
	    String restCall = response.getBody();
	    
	    
	    System.out.println(status);
	    
	    System.out.println(restCall);
	}
	
	private MeasurementData mockData() {
		
		Domain domain = new Domain();
		domain.setName(DOMAIN_TYPE.FIRST_FLOOR.getValue());
		
		DeviceInformation deviceInformation = new DeviceInformation();
		deviceInformation.setName(DEVICEINFORMATION_TYPE.SENSOR.getValue());

		MeasurementData data = new MeasurementData();
		MeasurementPoint point = new MeasurementPoint(domain, deviceInformation);
		
		data.add(point);
		
		return data;

	}
}

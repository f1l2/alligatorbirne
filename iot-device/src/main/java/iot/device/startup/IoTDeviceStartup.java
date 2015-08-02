package iot.device.startup;

import iot.device.IotDevice;
import iot.device.status.Status;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IoTDeviceStartup implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	final static Logger logger = Logger.getLogger(IoTDeviceStartup.class);
	
	@Autowired
	private Status status;
	
	@Autowired
	private IotDevice iotDevice;
	
	
	@Override
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {
		
		String port = String.valueOf(arg0.getEmbeddedServletContainer().getPort());

		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error(e);
		}
		
		iotDevice.setProperties(ip, port);
		
		iotDevice.informCM();
		
	}
}
	


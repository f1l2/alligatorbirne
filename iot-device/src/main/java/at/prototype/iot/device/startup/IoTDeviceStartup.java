package at.prototype.iot.device.startup;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IoTDeviceStartup implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
	
	final static Logger logger = Logger.getLogger(IoTDeviceStartup.class);

	@Override
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {
		
		
		System.out.println("PORT: " + arg0.getEmbeddedServletContainer().getPort());
		
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	


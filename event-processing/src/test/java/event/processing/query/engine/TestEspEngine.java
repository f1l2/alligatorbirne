package event.processing.query.engine;

import java.util.Random;

import org.junit.Test;

import at.prototype.common.data.DeviceInformation;
import event.processing.DeviceInformationImpl;
import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;

public class TestEspEngine {

	private static Random generator = new Random();
	 
	  private void delay(long time) {
	    	try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
	  private static DeviceInformation generateRandomInformation() {
		  
		  DeviceInformation device = new DeviceInformationImpl();

	        return device;
	    }
	 
	   
	    @Test
	    public void testEngine() {

	    	
	    	EngineFactory factory = new EngineFactory();
	    	
	    	Engine engine = factory.createEngine();
	    	engine.initialize();
	    	
	    	engine.sendEvent(TestEspEngine.generateRandomInformation());
	    	
	    	delay(1000);
	    	
	    	engine.registerQuery("select * from DeviceInformationImpl");
	    	
	    	delay(1000);
	    	
	    	engine.sendEvent(TestEspEngine.generateRandomInformation());
	    	
	    	delay(1000);
	    	
	    	engine.sendEvent(TestEspEngine.generateRandomInformation());
	    	
	    }
}

package event.processing.engine.impl;

import at.prototype.common.data.DeviceInformation;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import event.processing.DeviceInformationImpl;
import event.processing.engine.Engine;
import event.processing.query.Query;

public class EsperEngine implements Engine {

	private static final String ENGINE_NAME = "myCEPEngine";
	
	private EPRuntime cepRT;
	
	private EPServiceProvider cep;
	
	@Override
	public void initialize() {
		
		
	    Configuration cepConfig = new Configuration();
	    
	    cepConfig.addEventType("DeviceInformationImpl" , DeviceInformationImpl.class.getName());
	
	    this.cep = EPServiceProviderManager.getProvider(ENGINE_NAME,cepConfig);
	     
	    this.cepRT = cep.getEPRuntime();
	
	}

	@Override
	public void registerQuery(Query query) {
		
	}
	
	@Override
	public void registerQuery(String query) {
		
		EPAdministrator cepAdm = this.cep.getEPAdministrator();
		EPStatement cepStatement = cepAdm.createEPL(query);
		 
		cepStatement.addListener(new CEPListener());
	}

	@Override
	public void sendEvent(DeviceInformation deviceInformation) {
		this.cepRT.sendEvent(deviceInformation);
	}
}

package event.processing.engine;

import common.data.DeviceInformation;

import event.processing.query.Query;

public abstract class Engine {
	
	private ENGINE_TYPE type;
	
	protected abstract void initialize();
	
	public abstract void registerQuery(Query query);

	public abstract void registerQuery(String query);
	
	public abstract void unregisterQuery(String query);
	
	public abstract void sendEvent(DeviceInformation deviceInformation);

	public ENGINE_TYPE getType() {
		return type;
	}
	
	public Engine(ENGINE_TYPE type) {
		this.type = type;
		this.initialize();
	}
}

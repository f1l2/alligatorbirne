package event.processing.engine;

import at.prototype.common.data.DeviceInformation;
import event.processing.query.Query;

public interface Engine {
	
	public void initialize();
	
	public void registerQuery(Query query);

	public void registerQuery(String query);
	
	public void sendEvent(DeviceInformation deviceInformation);
	
}

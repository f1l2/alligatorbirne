package event.processing.engine;


public abstract class EngineFactory {
	
	protected static Engine INSTANCE = null;
	protected static Object MUTEX = new Object();
	
	protected abstract Engine createEngine();
	
	public abstract Engine getEngine();

	public Boolean isTypeEqual(ENGINE_TYPE type) {
		
		if ((null != INSTANCE) && (INSTANCE.getType().equals(type))) {
			return true;
		}
		return false;
	}
}



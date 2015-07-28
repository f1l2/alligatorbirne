package event.processing.engine.impl;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;

public class EsperEngineFactory extends EngineFactory {

	@Override
	protected Engine createEngine() {
		return new EsperEngine();
	}

	@Override
	public Engine getEngine() {
		if (null == INSTANCE) {
			synchronized (MUTEX) {
				if (null == INSTANCE) {
					INSTANCE = createEngine();
				}
			}
		}			
		return INSTANCE;
	}
}

package event.processing.engine;

import event.processing.engine.impl.EsperEngine;

public class EngineFactory {

	
	public Engine createEngine() {

		return new EsperEngine();
		
	}
	
}

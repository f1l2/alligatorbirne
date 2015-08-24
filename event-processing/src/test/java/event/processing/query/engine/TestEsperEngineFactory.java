package event.processing.query.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import event.processing.engine.ENGINE_TYPE;
import event.processing.engine.Engine;
import event.processing.engine.impl.EsperEngine;
import event.processing.engine.impl.EsperEngineFactory;

public class TestEsperEngineFactory {
	
	private EsperEngineFactory factory;
	
	@Before
	public void before() {
		factory = new EsperEngineFactory();
	}
	
	@Test
	public void testGetEngine() {
		
		assertNotNull(factory);
		
		Engine engine = factory.getEngine();
		
		assertNotNull(engine);
		assertEquals(engine.getType(), ENGINE_TYPE.ESPER);
		assertEquals(engine, engine);

		
		if (!(engine instanceof EsperEngine)) {
			Assert.fail("Not instance of EsperEngine");
		}
	}

}

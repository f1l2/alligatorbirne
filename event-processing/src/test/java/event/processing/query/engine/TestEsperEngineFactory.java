package event.processing.query.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import event.processing.AbstractTestEP;
import event.processing.engine.ENGINE_TYPE;
import event.processing.engine.Engine;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;

public class TestEsperEngineFactory extends AbstractTestEP {

    @Test
    public void testGetEngine() {

        assertNotNull(factory);

        Engine engine = factory.getEngine();

        assertNotNull(engine);
        assertEquals(engine.getType(), ENGINE_TYPE.ESPER);
        assertEquals(engine, engine);

    }

    public void testGetListener() {

        EngineListener listener = factory.getEngineListener();

        assertNotNull(listener);

    }

    public void testGetQueryTransformer() {

        QueryTransformer transformer = factory.getQueryTransformer();

        assertNotNull(transformer);
    }

}

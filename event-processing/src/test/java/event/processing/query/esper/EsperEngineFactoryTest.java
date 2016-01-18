package event.processing.query.esper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.lang.rule.GenericListener;
import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.engine.ENGINE_TYPE;
import event.processing.engine.Engine;
import event.processing.engine.LanguageTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EsperEngineFactoryTest extends AbstractTestEP {

    @Test
    public void testGetEngine() {

        assertNotNull(factory);

        Engine engine = factory.getEngine();

        assertNotNull(engine);
        assertEquals(engine.getType(), ENGINE_TYPE.ESPER);
        assertEquals(engine, engine);

    }

    @Test
    public void testGetListener() {

        GenericListener listener = factory.getGenericListener();

        assertNotNull(listener);

    }

    @Test
    public void testGetQueryTransformer() {

        LanguageTransformer transformer = factory.getTransformer();

        assertNotNull(transformer);
    }

}

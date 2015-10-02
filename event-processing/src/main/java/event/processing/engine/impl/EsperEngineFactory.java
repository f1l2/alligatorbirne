package event.processing.engine.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.LanguageTransformer;

@Component("esper")
public class EsperEngineFactory extends EngineFactory {

    @Autowired
    protected EsperEngine engine;

    @Autowired
    protected EsperEPLTransformer transformer;

    @Autowired
    protected EsperEngineListener listener;

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public EngineListener getEngineListener() {
        return listener;
    }

    @Override
    public LanguageTransformer getTransformer() {
        return transformer;
    }

}

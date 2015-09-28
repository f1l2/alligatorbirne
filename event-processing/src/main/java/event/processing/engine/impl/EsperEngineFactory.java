package event.processing.engine.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;

@Component("esper")
public class EsperEngineFactory extends EngineFactory {

    @Autowired
    protected EsperEngine engine;

    @Autowired
    protected EsperEngineListener listener;

    @Autowired
    protected EsperQueryTransformer transformer;

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public EngineListener getEngineListener() {
        return listener;
    }

    @Override
    public QueryTransformer getQueryTransformer() {
        return transformer;
    }

}

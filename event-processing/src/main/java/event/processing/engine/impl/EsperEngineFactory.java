package event.processing.engine.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import common.lang.rule.GenericListener;
import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.LanguageTransformer;

@Component("esper")
public class EsperEngineFactory extends EngineFactory {

    @Autowired
    protected EsperEngine engine;

    @Autowired
    protected EsperEPLTransformer transformer;

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Autowired
    public GenericListener getGenericListener() {
        return new EsperEngineListener();
    }

    @Override
    public LanguageTransformer getTransformer() {
        return transformer;
    }

}

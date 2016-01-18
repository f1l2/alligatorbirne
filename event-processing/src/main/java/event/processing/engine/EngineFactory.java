package event.processing.engine;

import common.lang.rule.GenericListener;

public abstract class EngineFactory {

    public abstract Engine getEngine();

    public abstract GenericListener getGenericListener();

    public abstract LanguageTransformer getTransformer();

}

package event.processing.engine;

public abstract class EngineFactory {

    public abstract Engine getEngine();

    public abstract EngineListener getEngineListener();

    public abstract QueryTransformer getQueryTransformer();

}

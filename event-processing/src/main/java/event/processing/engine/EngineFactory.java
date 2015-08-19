package event.processing.engine;

public abstract class EngineFactory {

    protected static Object MUTEX = new Object();

    protected abstract Engine createEngine();

    public abstract Engine getEngine();

    protected abstract EngineListener createEgineListener();

    public abstract EngineListener getEngineListener();

    protected abstract QueryTransformer createQueryTransformer();

    public abstract QueryTransformer getQueryTransformer();

}

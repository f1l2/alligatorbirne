package event.processing.engine;

import java.util.List;

import common.data.DataSource;

public abstract class Engine {

    private ENGINE_TYPE type;

    protected abstract void initialize();

    public abstract void registerQuery(String query, EngineListener listener);

    public abstract void registerQuery(List<String> query, EngineListener listener);

    public abstract void unregisterQuery(String query);

    public abstract void unregisterQuery(List<String> query);

    public abstract void sendEvent(DataSource dataSource);

    public ENGINE_TYPE getType() {
        return type;
    }

    public Engine(ENGINE_TYPE type) {
        this.type = type;
        this.initialize();
    }

    public abstract void unregisterAll();

}

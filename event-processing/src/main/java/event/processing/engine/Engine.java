package event.processing.engine;

import java.util.List;

import common.data.model.DeviceData;

public abstract class Engine {

    private ENGINE_TYPE type;

    protected abstract void initialize();

    public abstract void register(String query, EngineListener listener);

    public abstract void register(List<String> query, EngineListener listener);

    public abstract void deregister(String query);

    public abstract void deregister(List<String> query);

    public abstract void send(DeviceData deviceData);

    public ENGINE_TYPE getType() {
        return type;
    }

    public Engine(ENGINE_TYPE type) {
        this.type = type;
        this.initialize();
    }

}

package event.processing.engine;

import java.util.List;

import common.data.model.DeviceData;
import common.lang.rule.GenericListener;

public abstract class Engine {

    private ENGINE_TYPE type;

    protected abstract void initialize();

    public abstract void register(String query, GenericListener listener);

    public abstract void register(List<String> query, GenericListener listener);

    public abstract void deregister(String query);

    public abstract void deregister(List<String> query);

    public abstract void send(DeviceData deviceData);

    public abstract void deregisterAll();

    public ENGINE_TYPE getType() {
        return type;
    }

    public Engine(ENGINE_TYPE type) {
        this.type = type;
        this.initialize();
    }

}

package event.processing.rest;

public interface EProcManageQuery {

    public void register(String query);

    public void unregister(String query);

    public void registerRule(String rule);

    public void unregisterRule(String rule);

}

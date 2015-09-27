package event.processing.rest;


public interface EProcManageQuery {

    public void register(String query);

    public void unregister(String query);

}

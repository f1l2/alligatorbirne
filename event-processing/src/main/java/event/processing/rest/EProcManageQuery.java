package event.processing.rest;

public interface EProcManageQuery {

    void register(String query);

    void unregister(String query);

}

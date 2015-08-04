package common.rest;

import common.data.configuration.ConnectionConfig;

public enum Url {

    IDEV_GET_ALL_CONFIGURATION("/configurations", RequestMethod.GET),
    //
    IDEV_GET_CONFIGURATION_BY_EP("/configurations/{id}", RequestMethod.GET),
    //
    IDEV_SET_CONFIGURATION("/configurations", RequestMethod.POST),
    //
    CMGMT_GET_ALL_DEVICES("/registrations/devices", RequestMethod.GET),
    //
    CMGMT_REGISTER_DEVICE_SOURCES("/registrations/devices/sources/{id}", RequestMethod.POST),
    //
    CMGMT_REGISTER_DEVICE("/registrations/devices", RequestMethod.POST),
    //
    CMGMT_HEART_BEAT_DEVICE("/registrations/devices/{id}", RequestMethod.PUT),
    //
    CMGMT_GET_ALL_EVENT_PROCESSING("/registrations/eventprocessing", RequestMethod.GET),
    //
    CMGMT_REGISTER_EVENT_PROCESSING("/registrations/eventprocessing", RequestMethod.POST),
    //
    CMGMT_HEART_BEAT_EVENT_PROCESSING("/registrations/eventprocessing/{id}", RequestMethod.PUT),
    //
    CMGMT_DELEGATION("/delegation/{id}", RequestMethod.POST),
    //
    EPROCESSING_SEND("/send", RequestMethod.POST);

    private String path;

    private RequestMethod requestMethod;

    Url(String path, RequestMethod requestMethod) {
        this.path = path;
        this.requestMethod = requestMethod;
    }

    public String getPath() {
        return path;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public String getUrl(String ip, String port) {
        return String.format("%s:%s%s", ip, port, path);
    }

    public String getUrl(ConnectionConfig config) {
        return String.format("%s%s", config.getUrl(), path);
    }

    public String getUrl(String url) {
        return String.format("%s%s", url, path);
    }

    public String getLogMessage() {
        return String.format("%s %s is invoked.", requestMethod, path);
    }

    public enum RequestMethod {
        GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
    }

}

package common.rest;

public enum RESOURCE_NAMING {

    IDEV_GET_ALL_CONFIGURATION("/configurations", RequestMethod.GET),
    //
    IDEV_GET_CONFIGURATION_BY_EP("/configurations/{authority}", RequestMethod.GET),
    //
    IDEV_SET_CONFIGURATION("/configurations", RequestMethod.POST),
    //
    CMGMT_GET_ALL_DEVICES("/registrations/devices", RequestMethod.GET),
    //
    CMGMT_REGISTER_DEVICE_SOURCES("/registrations/devices/sources/{id}", RequestMethod.POST),
    //
    CMGMT_GET_DEVICE_DATA_SOURCES("/registrations/devices/sources/{id}", RequestMethod.GET),
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
    EPROCESSING_SEND("/send", RequestMethod.POST),
    //
    EPROCESSING_REGISTRATION_QUERY("/registrations/query/{name}", RequestMethod.POST),
    //
    EPROCESSING_DEREGISTRATION_QUERY("/deregistrations/query/{name}", RequestMethod.GET),
    //
    EPROCESSING_REGISTRATION_RULE("/registrations/rule/{name}", RequestMethod.POST),
    //
    EPROCESSING_DEREGISTRATION_RULE("/deregistrations/rule/{name}", RequestMethod.GET),
    //
    EPROCESSING_GET_ALL_QUERIES("/registrations/queries", RequestMethod.GET),
    //
    EPROCESSING_GET_ALL_RULES("/registrations/rules", RequestMethod.GET),
    //
    EPROCESSING_ACTIVATIONS_RULE("/activations/rule/{name}", RequestMethod.GET),
    //
    EPROCESSING_DEACTIVATIONS_RULE("/deactivations/rule/{name}", RequestMethod.GET);

    private String path;

    private RequestMethod requestMethod;

    RESOURCE_NAMING(String path, RequestMethod requestMethod) {
        this.path = path;
        this.requestMethod = requestMethod;
    }

    public String getPath() {
        return path;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public enum RequestMethod {
        GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
    }

}

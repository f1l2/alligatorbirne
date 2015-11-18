package common.rest;

public enum RESOURCE_NAMING {

    /**
     * Device relevant resources.
     */
    DEV_GET_ALL_CONFIGURATION("/configurations", RequestMethod.GET),
    //
    DEV_GET_CONFIGURATION_BY_EP("/configurations/{authority}", RequestMethod.GET),
    //
    DEV_SET_CONFIGURATION("/configurations", RequestMethod.POST),
    //
    DEV_START_DELIVERY("/delivery/start", RequestMethod.POST),
    //
    DEV_STOP_DELIVERY("/delivery/stop", RequestMethod.POST),
    //
    DEV_STOP_INSTANCE("/stopping", RequestMethod.POST),
    //

    /**
     * CM relevant resources.
     */
    CM_GET_ALL_DEVICES("/registrations/devices", RequestMethod.GET),
    //
    CM_REGISTER_DEVICE_SOURCES("/registrations/devices/sources/{id}", RequestMethod.POST),
    //
    CM_GET_DEVICE_DATA_SOURCES("/registrations/devices/sources/{id}", RequestMethod.GET),
    //
    CM_GET_DEVICE_BY_DATA_SOURCES("/registrations/devices/sources/{devInfo}/{domainInfo}", RequestMethod.GET),
    //
    CM_REGISTER_DEVICE("/registrations/devices", RequestMethod.POST),
    //
    CM_HEART_BEAT_DEVICE("/registrations/devices/{id}", RequestMethod.PUT),
    //
    CM_GET_ALL_EVENT_PROCESSING("/registrations/eventprocessing", RequestMethod.GET),
    //
    CM_REGISTER_EVENT_PROCESSING("/registrations/eventprocessing", RequestMethod.POST),
    //
    CM_REGISTER_EVENT_PROCESSING_SOURCES("/registrations/eventprocessing/sources", RequestMethod.POST),
    //
    CM_DEREGISTER_EVENT_PROCESSING_SOURCES("/deregistrations/eventprocessing/sources", RequestMethod.POST),
    //
    CM_GET_EVENT_PROCESSING_DATA_SOURCES("/registrations/devices/eventprocessing/{id}", RequestMethod.GET),
    //
    CM_HEART_BEAT_EVENT_PROCESSING("/registrations/eventprocessing/{id}", RequestMethod.PUT),
    //
    CM_DELEGATION("/delegation", RequestMethod.POST),
    //
    CM_REGISTRATION_QUERY("/registrations/query/{name}", RequestMethod.POST),
    //
    CM_DEREGISTRATION_QUERY("/deregistrations/query/{name}", RequestMethod.DELETE),
    //
    CM_REGISTRATION_RULE("/registrations/rule/{name}", RequestMethod.POST),
    //
    CM_DEREGISTRATION_RULE("/deregistrations/rule/{name}", RequestMethod.DELETE),
    //
    CM_GET_ALL_QUERIES("/registrations/queries", RequestMethod.GET),
    //
    CM_GET_ALL_RULES("/registrations/rules", RequestMethod.GET),
    //
    CM_ACTIVATIONS_RULE("/activations/rule/{name}", RequestMethod.GET),
    //
    CM_DEACTIVATIONS_RULE("/deactivations/rule/{name}", RequestMethod.GET),

    /**
     * EP relevant resources.
     */
    EP_SEND("/send", RequestMethod.POST),
    //
    EP_REGISTRATION_QUERY("/registrations/query/{name}", RequestMethod.POST),
    //
    EP_DEREGISTRATION_QUERY("/deregistrations/query/{name}", RequestMethod.DELETE),
    //
    EP_REGISTRATION_RULE("/registrations/rule/{name}", RequestMethod.POST),
    //
    EP_DEREGISTRATION_RULE("/deregistrations/rule/{name}", RequestMethod.DELETE),
    //
    EP_GET_ALL_QUERIES("/registrations/queries", RequestMethod.GET),
    //
    EP_GET_ALL_RULES("/registrations/rules", RequestMethod.GET),
    //
    EP_ACTIVATIONS_RULE("/activations/rule/{name}", RequestMethod.GET),
    //
    EP_DEACTIVATIONS_RULE("/deactivations/rule/{name}", RequestMethod.GET);

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

    /**
     * According to the REST style it is sufficient to work with following request methods.
     * 
     */
    public enum RequestMethod {
        GET, DELETE, POST, PUT
    }

}

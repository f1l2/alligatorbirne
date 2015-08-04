package common.rest;

public enum Url {


	IDEV_GET_ALL_CONFIGURATION("/configurations", RequestMethod.GET),
	IDEV_GET_CONFIGURATION_BY_EP("/configurations/{id}", RequestMethod.GET),
	IDEV_SET_CONFIGURATION("/configurations", RequestMethod.POST),
	
	
	CM_DELEGATION("/delegation", RequestMethod.POST ),
	CM_SUBSCRIPTION("/subscription", RequestMethod.POST),
	CM_GET_ALL_DEVICES("/registrations", RequestMethod.GET),
	CM_GET_DEVICE("/registrations/{id}", RequestMethod.GET),
	CM_REGISTER_DEVICE("/registrations", RequestMethod.POST);

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


	public enum RequestMethod {

		GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE

	}
	

}

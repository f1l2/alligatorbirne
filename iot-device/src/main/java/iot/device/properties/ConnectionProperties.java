package iot.device.properties;

public class ConnectionProperties {
	
	private COMPONENT type;
	
	private Long id;
	
	private String name;
	
	private String ip;
	
	private String port;
	
	private String url;
	
	public ConnectionProperties(COMPONENT type, Long id, String name, String ip, String port) {
		
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.url = ip + ":" + port;
	}
	
	public COMPONENT getType() {
		return type;
	}

	public void setType(COMPONENT type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

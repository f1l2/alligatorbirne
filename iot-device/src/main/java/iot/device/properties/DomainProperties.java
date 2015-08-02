package iot.device.properties;

import common.data.Domain;

public class DomainProperties extends Domain {
	
	private Domain domain;
	
	public DomainProperties(Domain domain) {
		
		this.domain = domain;

	}

	public Domain getDomain() {
		return domain;
	}
	
}

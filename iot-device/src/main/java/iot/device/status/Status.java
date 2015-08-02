package iot.device.status;

import org.springframework.stereotype.Component;

@Component
public class Status {
	
	private STATUS_TYPE current = STATUS_TYPE.UNDEFINIED;

	public STATUS_TYPE getCurrent() {
		return current;
	}

	public void setCurrent(STATUS_TYPE current) {
		this.current = current;
	}
}

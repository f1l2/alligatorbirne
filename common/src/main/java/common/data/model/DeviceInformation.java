package common.data.model;

import common.data.type.DEVICE_INFORMATION_TYPE;

public class DeviceInformation extends DataModel {

    private static final long serialVersionUID = 4765728902220294666L;

    private DEVICE_INFORMATION_TYPE type;

    public DEVICE_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DEVICE_INFORMATION_TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DeviceInformation [id=" + id + ", name=" + name + ", type=" + type + "]";
    }
}

package common.data.model;

import common.data.type.DEVICE_INFORMATION_TYPE;

public class DeviceInformation extends DataModel {

    private String value;

    private DEVICE_INFORMATION_TYPE type;

    public DEVICE_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DEVICE_INFORMATION_TYPE type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

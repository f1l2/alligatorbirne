package common.data.model;

import common.data.type.DEVICE_INFORMATION_TYPE;

public class DeviceInformation extends DataModel {

    private Integer value;

    private DEVICE_INFORMATION_TYPE type;

    public DEVICE_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DEVICE_INFORMATION_TYPE type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DeviceInformation [id=" + id + ", name=" + name + ", value=" + value + ", type=" + type + "]";
    }
}

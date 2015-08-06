package common.data;

import common.data.type.DEVICE_INFORMATION_TYPE;

public class DeviceInformation extends DataModel {

    private String name;

    private DEVICE_INFORMATION_TYPE type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DEVICE_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DEVICE_INFORMATION_TYPE type) {
        this.type = type;
    }

}

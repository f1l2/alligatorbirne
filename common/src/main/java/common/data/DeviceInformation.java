package common.data;

import common.data.type.DEVICE_INFORMATION_TYPE;

public class DeviceInformation extends DataModel {

    private DEVICE_INFORMATION_TYPE type;

    public DEVICE_INFORMATION_TYPE getType() {
        return type;
    }

    public void setType(DEVICE_INFORMATION_TYPE type) {
        this.type = type;
    }

}

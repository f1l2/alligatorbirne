package event.processing.rest;

import common.data.model.DeviceData;

public interface EProcManageData {

    public void receive(DeviceData deviceData);

}

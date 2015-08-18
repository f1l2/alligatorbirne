package event.processing.rest;

import common.data.DeviceInformation;

public interface EProcManageData {

    public void receive(DeviceInformation deviceInformation);

}

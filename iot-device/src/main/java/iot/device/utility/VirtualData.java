package iot.device.utility;

import java.time.Instant;

import common.data.dto.DeviceDataDTO;

public class VirtualData {

    private DeviceDataDTO devData;

    private String url;

    private Instant timeStamp;

    public VirtualData(DeviceDataDTO devData, String url, Instant timeStamp) {
        this.setDevData(devData);
        this.url = url;
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public DeviceDataDTO getDevData() {
        return devData;
    }

    public void setDevData(DeviceDataDTO devData) {
        this.devData = devData;
    }

}

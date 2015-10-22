package iot.device.utility;

import java.time.Instant;

import common.data.model.DeviceData;

public class VirtualData {

    private DeviceData devData;

    private String url;

    private Instant timeStamp;

    public VirtualData(DeviceData devData, String url, Instant timeStamp) {
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

    public DeviceData getDevData() {
        return devData;
    }

    public void setDevData(DeviceData devData) {
        this.devData = devData;
    }

}

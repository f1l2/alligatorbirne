package iot.device.utility;

import java.time.Instant;

import common.data.model.DeviceInformation;

public class VirtualData {

    private DeviceInformation devInfo;

    private String url;

    private Instant timeStamp;

    public VirtualData(DeviceInformation devInfo, String url, Instant timeStamp) {
        this.devInfo = devInfo;
        this.url = url;
        this.timeStamp = timeStamp;
    }

    public DeviceInformation getDevInfo() {
        return devInfo;
    }

    public void setDevInfo(DeviceInformation devInfo) {
        this.devInfo = devInfo;
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

}

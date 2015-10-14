package iot.device.vt;

import java.util.Date;

import common.data.DeviceInformation;

public class VtData {

    private DeviceInformation devInfo;

    private String url;

    private Date timeStamp;

    public VtData(DeviceInformation devInfo, String url, Date timeStamp) {
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

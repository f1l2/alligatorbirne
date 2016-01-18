package common.data.dto;

import common.data.model.DeviceData;

public class RuleDTO {

    private String name;

    private String rule;

    private boolean isActive;

    private DeviceData deviceData;

    public DeviceData getDeviceData() {
        return deviceData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }

}

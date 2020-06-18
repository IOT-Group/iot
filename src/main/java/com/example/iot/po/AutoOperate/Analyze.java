package com.example.iot.po.AutoOperate;

public class Analyze {
    private String deviceType;
    private String deviceId;
    private String condition;
    private String state;

    public Analyze(String deviceType, String deviceId, String condition, String state) {
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.condition = condition;
        this.state = state;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getCondition() {
        return condition;
    }

    public String getState() {
        return state;
    }
}

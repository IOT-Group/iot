package com.example.iot.po.AutoOperate;

public class Calendar {
    private String deviceId;
    private String time;
    private String code;

    public Calendar(String deviceId, String time, String code) {
        this.deviceId = deviceId;
        this.time = time;
        this.code = code;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getTime() {
        return time;
    }

    public String getCode() {
        return code;
    }
}

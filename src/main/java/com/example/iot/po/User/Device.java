package com.example.iot.po.User;

public class Device {
    private String deviceId;
    private String deviceType;  //类型:A,B,C,H,L,T
    private String power;   //功率
    private String voltage;     //电压
    private String state;   //状态，是否开启

    public Device(String deviceId, String deviceType, String power, String voltage, String state) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.power = power;
        this.voltage = voltage;
        this.state = state;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package com.example.iot.po.User;

public class Environment {
    private String time;
    private String temperature;
    private String humidity;
    private String ownerState;
    public Environment(){

    }

    public Environment(String time, String temperature, String humidity, String ownerState) {
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.ownerState = ownerState;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getOwnerState() {
        return ownerState;
    }

    public void setOwnerState(String ownerState) {
        this.ownerState = ownerState;
    }
}

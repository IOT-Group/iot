package com.example.iot.vo;

public class Environment {
    private String username;
    private String time;
    private String temperature;
    private String humidity;
    private String ownerState;
    private String timeInterval;

    public Environment(){

    }

    public Environment(String username, String time, String temperature, String humidity, String ownerState, String timeInterval) {
        this.username = username;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.ownerState = ownerState;
        this.timeInterval = timeInterval;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }
}

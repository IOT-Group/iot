package com.example.iot.po.User;

public class Environment {
    private String time;
    private String temperature;
    private String humidity;
    private String ownerState;

    public Environment(String time, String temperature, String humidity, String ownerState) {
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.ownerState = ownerState;
    }


}

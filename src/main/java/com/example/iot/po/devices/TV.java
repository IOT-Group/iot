package com.example.iot.po.devices;

public class TV extends device{
    String name="TV";
    public TV(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
}

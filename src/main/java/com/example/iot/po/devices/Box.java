package com.example.iot.po.devices;

public class Box extends device{
    String type="Box";
    public Box(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
}

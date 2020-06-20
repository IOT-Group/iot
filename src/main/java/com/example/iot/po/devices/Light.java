package com.example.iot.po.devices;

public class Light extends device {
    String name="Light";
    public Light(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
}

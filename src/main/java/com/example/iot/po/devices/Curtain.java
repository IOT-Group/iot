package com.example.iot.po.devices;

public class Curtain extends  device{
    String name ="Curtain";
    public Curtain(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
}

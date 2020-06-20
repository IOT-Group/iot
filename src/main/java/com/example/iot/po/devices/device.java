package com.example.iot.po.devices;

public class device {

    public device() {

    }

    public int getId() {
        return id;
    }

    int id=0;
    String name="device";

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    int state=0;
    public device(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
    public void update(int time){

    };
}

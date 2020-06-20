package com.example.iot.vo;

public class AddDeviceResponse {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    private String typeid;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    private int state=0;

}

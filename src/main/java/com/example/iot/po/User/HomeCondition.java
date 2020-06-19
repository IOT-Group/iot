package com.example.iot.po.User;

import java.util.ArrayList;
import java.util.List;

public class HomeCondition {
    List<Device> devices=new ArrayList<>();
    Environment environment;

    public HomeCondition(Environment environment,List<Device> devices) {
        this.devices = devices;
        this.environment = environment;
    }
}

package com.example.iot.dao;

import com.example.iot.dao.Repository.DeviceManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.iot.po.devices.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceManagementDao implements DeviceManagementRepository {
    int latesttime=0;
    ArrayList<device> runningdevices=new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int addDevice(String type, String owner) {
        int id=jdbcTemplate.update("insert into device (`type`,`owner`) values (?,?)",type,owner);
        return id;
    }

    @Override
    public int deleteDevice(String deviceId) {
        jdbcTemplate.update("delete from device deviceid=\""+ deviceId + "\"");
        return 1;
    }

    @Override
    public int operateDevice(String time, String code, String deviceId) {
        int now=Integer.parseInt(time);
        int gap=now-latesttime;         //上次操作距这次操作的时间，用于决定设备运行的效果
        int flag=0;       //默认运行设备列表中无当前操作设备
        for (com.example.iot.po.devices.device device : runningdevices) {
            device.update(gap);
        }
        for(com.example.iot.po.devices.device device : runningdevices){
            if(device.getId()==Integer.parseInt(deviceId)){
                device.setState(Integer.parseInt(code));
                flag=1;
            }
        }
        if(flag==0){
            device d1=new device();
            String type=jdbcTemplate.queryForObject("select owner from device where id= ?",String.class,deviceId);
            switch (type){
                case "AirConditioner":d1=new AirConditioner(code,deviceId);break;
                case "Light":d1=new Light(code,deviceId);break;
                case "Curtain":d1=new Curtain(code, deviceId);break;
                case "Humidifier":d1=new Humidifier(code, deviceId);break;
                case "TV":d1=new TV(code, deviceId);break;
                case "Box":d1=new Box(code, deviceId);break;
            }
            runningdevices.add(d1);
        }
        return 1;
    }
}

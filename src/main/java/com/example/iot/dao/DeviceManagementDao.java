package com.example.iot.dao;

import com.example.iot.dao.Repository.DeviceManagementRepository;
import com.example.iot.vo.AddDeviceResponse;
import com.example.iot.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.iot.po.devices.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceManagementDao implements DeviceManagementRepository {
    int latesttime=0;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    List<device> runningdevices=jdbcTemplate.query("select id and state from device where state !='0';",new deviceMapper());

    @Override
    public boolean initialize(){
        for (device d1: runningdevices) {
                int id=d1.getId();
                String type=jdbcTemplate.queryForObject("select type from device where id= ?",String.class,id);
                assert type != null;
                if(type.startsWith("A")){
                    d1=new AirConditioner(String.valueOf(d1.getState()),String.valueOf(d1.getState()));
                }

                else if(type.startsWith("L")) {
                    d1 = new Light(String.valueOf(d1.getState()),String.valueOf(d1.getState()));
                }
                else if(type.startsWith("C")) {
                    d1 = new Curtain(String.valueOf(d1.getState()),String.valueOf(d1.getState()));
                }
                else if(type.startsWith("H")) {
                    d1 = new Humidifier(String.valueOf(d1.getState()),String.valueOf(d1.getState()));
                }
                else if(type.startsWith("T")) {
                    d1 = new TV(String.valueOf(d1.getState()),String.valueOf(d1.getState()));
                }
                else if(type.startsWith("B")) {
                    d1 = new Box(String.valueOf(d1.getState()),String.valueOf(d1.getState()));
                }
        }
        return true;
    }
    @Override
    public AddDeviceResponse addDevice(String type, String owner) {
        AddDeviceResponse adr=new AddDeviceResponse();
        String userid=jdbcTemplate.queryForObject("select id from user where username = ?",String.class,owner);
        int id=jdbcTemplate.update("insert into device (`type`,`userId`,`state`) values (?,?,?)",type,userid,"0");
        adr.setId(String.valueOf(id));
        adr.setTypeid(type);
        return adr;
    }

    @Override
    public boolean deleteDevice(String deviceId) {
        int id=Integer.parseInt(deviceId);
        jdbcTemplate.update("delete from device where id=\""+ id + "\"");
        return true;
    }

    @Override
    public boolean operateDevice(String time, String code, String deviceId) {
        int now=Integer.parseInt(time);
        int gap=now-latesttime;         //上次操作距这次操作的时间，用于决定设备运行的效果
        latesttime=now;
        int flag=0;       //默认运行设备列表中无当前操作设备
        if(gap>0) {
            for (com.example.iot.po.devices.device device : runningdevices) {
                if (device.getState() != 0) {
                    device.update(gap);
                }
            }
        }
        for(com.example.iot.po.devices.device device : runningdevices){
            if(device.getId()==Integer.parseInt(deviceId)){
                device.setState(Integer.parseInt(code));
                int id=Integer.parseInt(deviceId);
                jdbcTemplate.update("update device set state =? where id= ?",code,id);
                flag=1;  //运行设备列表中找到当前操作设备
            }
        }
        if(flag==0){
            device d1=new device();
            int id=Integer.parseInt(deviceId);
            String type=jdbcTemplate.queryForObject("select type from device where id= ?",String.class,id);
            assert type != null;
            if(type.startsWith("A")){
                    d1=new AirConditioner(code,deviceId);
                }

                else if(type.startsWith("L")) {
                    d1 = new Light(code, deviceId);
                }
                else if(type.startsWith("C")) {
                    d1 = new Curtain(code, deviceId);
                }
                else if(type.startsWith("H")) {
                    d1 = new Humidifier(code, deviceId);
                }
                else if(type.startsWith("T")) {
                    d1 = new TV(code, deviceId);
                }
                else if(type.startsWith("B")) {
                    d1 = new Box(code, deviceId);
            }
            runningdevices.add(d1);   //新增运行状态设备
        }
        int userid=Integer.parseInt(jdbcTemplate.queryForObject("select userId from device where id=?",String.class,deviceId));
        String temperature=jdbcTemplate.queryForObject("select temperature from environment where userid=?",String.class,userid);
        String humidity=jdbcTemplate.queryForObject("select humidity from environment where userid=?",String.class,userid);
        jdbcTemplate.update("insert into operation (deviceid,`time`,code,temperature,humidity)value (?,?,?,?,?)",deviceId,time,code,temperature,humidity);
        return true;
    }
}

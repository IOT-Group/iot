package com.example.iot.po.devices;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AirConditioner extends device{
    String name="AirConditioner";
    public AirConditioner(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void update(int time) {
        int settemp = 0;
        int owner=jdbcTemplate.queryForObject("select owner from device where id= ?",Integer.class,id);

        int temp=jdbcTemplate.queryForObject("select temperature from environment e and device d where d.id= ? and d.owner=e.owner",Integer.class,id);
        if(state>32){
            settemp=state-16;
            temp= Math.min(settemp, temp + time / 2);
        }
        else {
            settemp=state;
            temp= Math.max(settemp, temp - time / 2);
        }
        jdbcTemplate.update("update  environment set temperature = ? where owner= ?",temp,owner);
    }
}

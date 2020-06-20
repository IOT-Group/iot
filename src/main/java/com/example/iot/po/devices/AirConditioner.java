package com.example.iot.po.devices;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public class AirConditioner extends device{
    String name="AirConditioner";
    public AirConditioner(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public void update(int time) {
        int settemp = 0;
        int owner=jdbcTemplate.queryForObject("select userId from device where id= ?",Integer.class,id);

        int temp=jdbcTemplate.queryForObject("select temperature from environment  where userid= ?",Integer.class,owner);
        if(state>32){
            settemp=state-16;
            temp= Math.min(settemp, temp + time / 2);
        }
        else {
            settemp=state;
            temp= Math.max(settemp, temp - time / 2);
        }
        jdbcTemplate.update("update  environment set temperature = ? where userid= ?",temp,owner);
    }
}

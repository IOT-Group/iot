package com.example.iot.po.devices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class Humidifier extends device{
    String name="Humidifier";
    public Humidifier(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void update(int time) {
        int owner=jdbcTemplate.queryForObject("select  from device where id= ? ",Integer.class,id);

        int hum=jdbcTemplate.queryForObject("select humidity from environment e and device d where d.id= ? and d.owner=e.owner",Integer.class,id);

        hum= Math.min(100, hum + time);
        jdbcTemplate.update("update  environment set temperature = ? where owner= ?",hum,owner);
    }
}

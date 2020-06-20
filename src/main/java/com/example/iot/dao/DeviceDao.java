package com.example.iot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int getOwner(int id){
        return jdbcTemplate.queryForObject("select userId from device where id= ?",Integer.class,id);
    }
    public int getTemp(int owner){
        return jdbcTemplate.queryForObject("select temperature from environment  where userid= ?",Integer.class,owner);
    }
    public void update(int temp,int owner){
        jdbcTemplate.update("update  environment set temperature = ? where userid= ?",temp,owner);
    }

    public int getHum(int owner){
        return jdbcTemplate.queryForObject("select humidity from environment where userid= ?",Integer.class,owner);
    }

    public void update2(int hum,int owner){
        jdbcTemplate.update("update  environment set humidity = ? where owner= ?",hum,owner);
    }



}

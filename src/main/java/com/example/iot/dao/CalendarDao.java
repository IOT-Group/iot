package com.example.iot.dao;

import com.example.iot.dao.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CalendarDao implements CalendarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addCalendar(String deviceId,String time,String code){
        jdbcTemplate.update("insert into calendar (`deviceid`,`time`,`code`) values (?,?,?)",deviceId,time,code);

    }

    @Override
    public void deleteCalendar(String deviceId,String time,String code){

    }
}

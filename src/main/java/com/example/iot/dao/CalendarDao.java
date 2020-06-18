package com.example.iot.dao;

import com.example.iot.dao.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDao implements CalendarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean addCalendar(String deviceId,String time,String code){
        jdbcTemplate.update("insert into `calendar` (`deviceid`,`time`,`code`) values (?,?,?)",deviceId,time,code);
        return true;
    }

    @Override
    public void deleteCalendar(String deviceId,String time,String code){
        jdbcTemplate.update("delete from `calendar` where `deviceid`=? and `time`=? and `code`=?",deviceId,time,code);
    }
}

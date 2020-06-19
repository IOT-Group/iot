package com.example.iot.dao;

import com.example.iot.dao.Repository.CalendarRepository;
import com.example.iot.po.AutoOperate.Calendar;
import com.example.iot.po.AutoOperate.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Calendar> showCalendar(String username){
        return jdbcTemplate.query(" select c.deviceid,c.time,c.code from calendar c where c.deviceid in (select d.id from device d,(select id from user where username=?)T1 where d.userid=T1.id);",new CalendarMapper(),username);
    }
}

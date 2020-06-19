package com.example.iot.service;

import com.example.iot.dao.Repository.CalendarRepository;
import com.example.iot.po.AutoOperate.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {
    @Autowired
    CalendarRepository calendarRepository;

    public boolean addCalendar(String deviceId,String time,String code){
        return calendarRepository.addCalendar(deviceId,time,code);
    }

    public void deleteCalendar(String deviceId,String time,String code){
        calendarRepository.deleteCalendar(deviceId,time,code);
    }

    public List<Calendar> showCalendar(String username){
        return calendarRepository.showCalendar(username);
    }
}

package com.example.iot.dao.Repository;

import com.example.iot.po.AutoOperate.Calendar;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository {
    public abstract boolean addCalendar(String deviceId,String time,String code);
    public abstract void deleteCalendar(String deviceId,String time,String code);
    public abstract List<Calendar> showCalendar(String username);
}

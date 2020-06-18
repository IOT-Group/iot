package com.example.iot.dao.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository {
    public abstract boolean addCalendar(String deviceId,String time,String code);
    public abstract void deleteCalendar(String deviceId,String time,String code);
}

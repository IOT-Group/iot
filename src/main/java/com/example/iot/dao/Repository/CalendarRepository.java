package com.example.iot.dao.Repository;

public interface CalendarRepository {
    public abstract void addCalendar(String deviceId,String time,String code);
    public abstract void deleteCalendar(String deviceId,String time,String code);
}

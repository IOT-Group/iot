package com.example.iot.po.AutoOperate;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class CalendarMapper implements RowMapper<Calendar> {
    @Override
    public Calendar mapRow(ResultSet resultSet, int i)throws SQLException {
        String deviceId=resultSet.getString("deviceid");
        String time=resultSet.getString("time");
        String code=resultSet.getString("code");
        return new Calendar(deviceId,time,code);
    }
}

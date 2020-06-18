package com.example.iot.po.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class DeviceMapper implements RowMapper<Device> {
    @Override
    public Device mapRow(ResultSet resultSet, int i)throws SQLException {
        String deviceType=resultSet.getString("type");
        String deviceId=resultSet.getString("deviceId");
        String power=resultSet.getString("power");
        String voltage=resultSet.getString("voltage");
        String state=resultSet.getString("state");
        return new Device(deviceId,deviceType,power,voltage,state);
    }
}

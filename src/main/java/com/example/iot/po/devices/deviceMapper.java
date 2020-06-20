package com.example.iot.po.devices;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class deviceMapper implements RowMapper<device>{
    @Override
    public device mapRow(ResultSet resultSet, int i) throws SQLException {
        String deviceId=resultSet.getString("id");
        String state=resultSet.getString("state");
        return new device(deviceId,state);
    }
}

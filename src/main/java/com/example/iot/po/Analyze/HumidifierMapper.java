package com.example.iot.po.Analyze;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
@Mapper
public class HumidifierMapper implements RowMapper<Humidifier>{
    @Override
    public Humidifier mapRow(ResultSet resultSet, int i)throws SQLException {
        String deviceId=resultSet.getString("deviceid");
        String code=resultSet.getString("code");
        return new Humidifier(deviceId,code);
    }
    
}

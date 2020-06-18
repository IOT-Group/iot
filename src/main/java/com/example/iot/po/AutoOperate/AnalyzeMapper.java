package com.example.iot.po.AutoOperate;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class AnalyzeMapper implements RowMapper<Analyze> {
    @Override
    public Analyze mapRow(ResultSet resultSet, int i)throws SQLException {
        String deviceType=resultSet.getString("type");
        String deviceId=resultSet.getString("deviceid");
        String condition=resultSet.getString("condition");
        String state=resultSet.getString("state");
        return new Analyze(deviceType,deviceId,condition,state);
    }
}
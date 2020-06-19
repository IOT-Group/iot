package com.example.iot.po.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class EnvironmentMapper implements RowMapper<Environment> {
    @Override
    public Environment mapRow(ResultSet resultSet, int i)throws SQLException {
        String time=resultSet.getString("time");
        String temperature=resultSet.getString("temperature");
        String humidity=resultSet.getString("humidity");
        String ownerState=resultSet.getString("ownerState");
        return new Environment(time,temperature,humidity,ownerState);
    }
}

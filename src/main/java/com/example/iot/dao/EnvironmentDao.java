package com.example.iot.dao;

import com.example.iot.dao.Repository.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnvironmentDao implements EnvironmentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void changeDegree(int id,int degree) {
        String sql="update environment set temperature = '"+degree+"' where userid='"+id+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void changeHumidity(int id,int humidity) {
        String sql="update environment set humidity = '"+humidity+"' where userid='"+id+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void changeHome(int id,int home) {
        String sql="update environment set ownerState = '"+home+"' where userid='"+id+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void changeTime(int id, int time) {
        String sql="update environment set time = '"+time+"' where userid='"+id+"'";
        jdbcTemplate.update(sql);
    }
}

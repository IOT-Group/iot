package com.example.iot.dao;

import com.example.iot.dao.Repository.UserRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Mapper
@Component
public class UserDao implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void register(String username,String password){
        String sql="insert into `iot`.`user` (username,password) values (\""+username+"\",\""+password+"\")";
        jdbcTemplate.update(sql);
    }

    @Override
    public boolean login(String username,String password){
        return true;
    }
}

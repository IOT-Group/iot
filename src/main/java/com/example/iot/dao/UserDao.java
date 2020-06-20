package com.example.iot.dao;

import com.example.iot.dao.Repository.EnvironmentRepository;
import com.example.iot.dao.Repository.UserRepository;
import com.example.iot.po.User.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public class UserDao implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    EnvironmentRepository environmentRepository;

    @Override
    public boolean register(String username,String password){
        int count=jdbcTemplate.queryForObject("select count(*) from user where username=?",Integer.class,username);
        if(count!=0)
            return false;
        String sql="insert into `iot`.`user` (username,password) values (\""+username+"\",\""+password+"\")";
        jdbcTemplate.update(sql);
        int userId=jdbcTemplate.queryForObject("select id from user where username=?",Integer.class,username);
        environmentRepository.addEnvironment(userId,25,30,1,0);
        return true;
    }

    @Override
    public boolean login(String username,String password){
        int count=jdbcTemplate.queryForObject("select count(*) from user where username=? and password=?",Integer.class,username,password);
        if(count==0)
            return false;
        else
            return true;
    }

    @Override
    public List<Device> getUserDevice(String username){
        List<Device> devices=jdbcTemplate.query("select T2.*,d2.power,d2.voltage from devicetype d2,(select d1.id as deviceId,type,state from device d1,(select id from user where username=?)T1 where d1.userId=T1.id)T2 where T2.type=d2.name;",new DeviceMapper(),username);
        return devices;
    }


}

package com.example.iot.dao;

import com.example.iot.dao.Repository.UserRepository;
import com.example.iot.po.User.Device;
import com.example.iot.po.User.DeviceMapper;
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

    @Override
    public boolean register(String username,String password){
        int count=jdbcTemplate.queryForObject("select count(*) from user where username=?",Integer.class,username);
        if(count!=0)
            return false;
        String sql="insert into `iot`.`user` (username,password) values (\""+username+"\",\""+password+"\")";
        jdbcTemplate.update(sql);
        return true;
    }

    @Override
    public boolean login(String username,String password){
        int count=jdbcTemplate.queryForObject("select count(*) from user where usename=? and password=?",Integer.class,username,password);
        if(count==0)
            return false;
        else
            return true;
    }


    public List<Device> getUserDevice(String username){
        List<Device> devices=jdbcTemplate.query("select T2.*,d2.power,d2.voltage from devicetype d2,(select d1.id as deviceId,type,state from device d1,(select id from user where username=?)T1 where d1.userId=T1.id)T2 where T2.type=d2.name;",new DeviceMapper(),username);
        return devices;
    }
}

package com.example.iot.po.devices;

import com.example.iot.dao.DeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class Humidifier extends device{
    @Autowired
    private DeviceDao deviceDao;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    String name="Humidifier";
    public Humidifier(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);

    }
    public Humidifier(){}

    public JdbcTemplate getJdbcTemplate(String password){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/iot?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT");
        ds.setUsername("root");
        ds.setPassword(password);
        return  new JdbcTemplate(ds);
    }


    @Override
    public void update(int time) {
        JdbcTemplate jdbcTemplate=getJdbcTemplate("19971204");
        int owner=jdbcTemplate.queryForObject("select userId from device where id= ? ",Integer.class,id);

        int hum=jdbcTemplate.queryForObject("select humidity from environment where userid= ?",Integer.class,owner);

        hum= Math.min(70, hum+(1-hum*hum/7000)* time);
        jdbcTemplate.update("update  environment set humidity = ? where userid= ?",hum,owner);
    }
}

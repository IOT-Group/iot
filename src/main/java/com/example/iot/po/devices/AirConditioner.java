package com.example.iot.po.devices;

import com.example.iot.dao.DeviceDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirConditioner extends device{
    @Autowired
    private DeviceDao deviceDao;



    String name="AirConditioner";
    public AirConditioner(){}
    public AirConditioner(String code,String deviceId){
        id=Integer.parseInt(deviceId);
        state=Integer.parseInt(code);
        System.out.println("pppppppppppppppppppppppppppppppppppppp"+state);

    }
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
        int settemp = 0;
        JdbcTemplate jdbcTemplate=getJdbcTemplate("root");
        int owner=jdbcTemplate.queryForObject("select userId from device where id= ?",Integer.class,id);

        int temp=jdbcTemplate.queryForObject("select temperature from environment  where userid= ?",Integer.class,owner);
        if(state>100){
            settemp=state-16;
            temp= Math.min(settemp, temp + time / 2);
        }
        else {
            settemp=state;
            temp= Math.max(settemp, temp - time / 2-temp/60*time);
        }
        jdbcTemplate.update("update  environment set temperature = ? where userid= ?",temp,owner);
    }
}

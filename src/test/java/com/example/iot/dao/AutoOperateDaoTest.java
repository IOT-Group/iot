package com.example.iot.dao;

import com.example.iot.po.User.Environment;
import com.example.iot.po.User.HomeCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoOperateDaoTest {
    @Autowired
    AutoOperateDao autoOperateDao;

    @Test
    public void autoOperateTest(){
        autoOperateDao.autoOperate("benson","720","36","10","1","30");
    }

    @Test
    public void getHomeConditionTest(){
        HomeCondition homeCondition=autoOperateDao.getHomeCondition("benson");
        System.out.println(homeCondition.getDevices().size());
        Environment environment=homeCondition.getEnvironment();
        System.out.println(environment.getTemperature());
        System.out.println(environment.getHumidity());
        System.out.println(environment.getTime());
        System.out.println(environment.getOwnerState());

    }
}

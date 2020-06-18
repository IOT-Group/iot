package com.example.iot.dao;

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
}

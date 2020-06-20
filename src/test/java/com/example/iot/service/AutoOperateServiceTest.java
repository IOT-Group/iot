package com.example.iot.service;

import com.example.iot.po.User.HomeCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoOperateServiceTest {
    @Autowired
    AutoOperateService autoOperateService;

    @Test
    public void autoOperateTest(){
        HomeCondition homeCondition=autoOperateService.autoOperate("benson","300","30","25","1","5");

    }

}

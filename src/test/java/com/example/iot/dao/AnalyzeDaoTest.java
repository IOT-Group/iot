package com.example.iot.dao;

import com.example.iot.dao.Repository.AnalyzeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyzeDaoTest {
    @Autowired
    AnalyzeDao analyzeDao;

    @Test
    public void analyzeHumidifierTest() {
        analyzeDao.analyzeHumidifier("1","H_PowerOff_55");
        analyzeDao.analyzeHumidifier("1","H_PowerOn_25");
    }

    @Test
    public void analyzeAirConditionerTest(){
        analyzeDao.analyzeAirConditioner("5","H_PowerOn_33_25");
    }

    @Test
    public void analyzeCurtainTest(){
        analyzeDao.analyzeCurtain("6","C_PowerOff");
        analyzeDao.analyzeCurtain("6","C_PowerOn");
    }
}

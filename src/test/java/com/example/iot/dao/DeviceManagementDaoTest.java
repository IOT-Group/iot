package com.example.iot.dao;

import com.example.iot.dao.Repository.DeviceManagementRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceManagementDaoTest {
    @Autowired
    DeviceManagementRepository deviceManagementRepository;

    @Test
    public void operateDeviceTest(){
        deviceManagementRepository.operateDevice("300","1","2");
    }
}

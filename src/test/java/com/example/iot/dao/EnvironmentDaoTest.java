package com.example.iot.dao;

import com.example.iot.dao.Repository.EnvironmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EnvironmentDaoTest {

    @Autowired
    EnvironmentDao environmentDao;



    @Test
    void changeDegree() {
        environmentDao.changeDegree(1,50);

    }

    @Test
    void changeHumidity() {
    }

    @Test
    void changeHome() {
    }

    @Test
    void changeTime() {
    }
}
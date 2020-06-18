package com.example.iot.service;

import com.example.iot.service.Recognition.FaceRecognition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FaceRecServiceTest {

    @Autowired
    FaceRecService faceRecService;

    @Test
    void recognition_string() {
        faceRecService.recognition_string("1",1);
    }
}
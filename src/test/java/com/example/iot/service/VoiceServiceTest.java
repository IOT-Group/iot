package com.example.iot.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class VoiceServiceTest {
    @Autowired
    VoiceService voiceService;

    @Test
    void analyzeVoice() {

        //voiceService.analyzeVoice("打开空调",1);
    }
}
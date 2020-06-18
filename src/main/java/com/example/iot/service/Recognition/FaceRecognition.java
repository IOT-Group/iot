package com.example.iot.service.Recognition;

import org.springframework.stereotype.Repository;

@Repository
public interface FaceRecognition {
    public void recognition_string(String input,int id);
}

package com.example.iot.controller;

import com.example.iot.service.FaceRecService;
import com.example.iot.service.Recognition.FaceRecognition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Recognition")
public class RecognitionController {
    @Autowired
    FaceRecognition faceRecognition;

    @RequestMapping("/rec")
    public void rec(@RequestParam(value = "voiceInput",required = true) String Input, @RequestParam(value = "username",required = true) String username,@RequestParam(value = "timeInterval",required = true) String timeInterval){
        faceRecognition.recognition_string(Input,username,timeInterval);
    }


}

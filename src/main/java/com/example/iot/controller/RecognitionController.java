package com.example.iot.controller;

import com.example.iot.service.FaceRecService;
import com.example.iot.service.Recognition.FaceRecognition;
import com.example.iot.vo.Response;
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
    public Response rec(@RequestParam(value = "state",required = true) String state, @RequestParam(value = "username",required = true) String username,@RequestParam(value = "time",required = true) String time, @RequestParam(value = "timeInterval",required = true) String timeInterval){
        return Response.ResponseSuccess(faceRecognition.recognition_string(state,username,timeInterval,time));
    }


}

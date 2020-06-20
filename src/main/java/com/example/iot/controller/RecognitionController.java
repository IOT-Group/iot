package com.example.iot.controller;

import com.example.iot.service.FaceRecService;
import com.example.iot.service.Recognition.FaceRecognition;
import com.example.iot.vo.RecVO;
import com.example.iot.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecognitionController {
    @Autowired
    FaceRecognition faceRecognition;

    @CrossOrigin
    @PostMapping(value = "/rec")
    @ResponseBody
    public Response rec(@RequestBody RecVO recVO){
        return Response.ResponseSuccess(faceRecognition.recognition_string(recVO.getState(),recVO.getUsername(),recVO.getTimeInterval(),recVO.getTime()));
    }


}

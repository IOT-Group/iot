package com.example.iot.controller;


import com.example.iot.service.Voice.AnalyzeVoice;
import com.example.iot.service.VoiceService;
import com.example.iot.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VoiceController {

    @Autowired
    AnalyzeVoice analyzeVoice;

    @CrossOrigin
    @PostMapping(value = "/voice")
    @ResponseBody
    public Response analyzeVoice(@RequestParam(value = "voiceInput",required = true) String voiceInput, @RequestParam(value = "username",required = true) String username, @RequestParam(value = "time",required = true) String time){
        return Response.ResponseSuccess(analyzeVoice.analyzeVoice(voiceInput,username,time));
    }

}

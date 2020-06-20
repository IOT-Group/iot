package com.example.iot.controller;


import com.example.iot.service.Voice.AnalyzeVoice;
import com.example.iot.service.VoiceService;
import com.example.iot.vo.Response;
import com.example.iot.vo.VoiceVO;
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
    public Response analyzeVoice(@RequestBody VoiceVO voice){
        return Response.ResponseSuccess(analyzeVoice.analyzeVoice(voice.getVoiceInput(),voice.getUsername(),voice.getTime()));
    }

}

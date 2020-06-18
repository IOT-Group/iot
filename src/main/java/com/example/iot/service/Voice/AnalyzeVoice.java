package com.example.iot.service.Voice;

import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeVoice {
    public void analyzeVoice(String voice_input,String username);

}

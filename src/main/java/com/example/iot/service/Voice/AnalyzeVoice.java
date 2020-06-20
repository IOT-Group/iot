package com.example.iot.service.Voice;

import com.example.iot.vo.DeviceVO;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeVoice {
    public DeviceVO analyzeVoice(String voice_input, String username, String time);

}

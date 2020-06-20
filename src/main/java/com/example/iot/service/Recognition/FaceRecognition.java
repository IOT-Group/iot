package com.example.iot.service.Recognition;

import com.example.iot.po.User.HomeCondition;
import com.example.iot.vo.DeviceVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaceRecognition {
    public HomeCondition recognition_string(String input, String username, String timeInterval, String time);
}

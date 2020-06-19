package com.example.iot.service.Recognition;

import com.example.iot.vo.DeviceVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaceRecognition {
    public List<DeviceVO> recognition_string(String input, String username, String timeInterval);
}

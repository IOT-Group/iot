package com.example.iot.service;

import com.example.iot.dao.Repository.AnalyzeRepository;
import com.example.iot.service.Voice.AnalyzeVoice;
import com.example.iot.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoiceService implements AnalyzeVoice {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DeviceManagementService deviceManagementService;

    @Autowired
    AnalyzeRepository  analyzeRepository;

    @Override
    public DeviceVO analyzeVoice(String voice_input,String username,String time) {

        String sql="SELECT id from user where username='"+username+"'";
        List<String> user=jdbcTemplate.queryForList(sql,String.class);

        int id=Integer.valueOf(user.get(0));


        if(voice_input.contains("开")&&!voice_input.contains("模式")){

            String device;
            if(voice_input.contains("到")){
                device=voice_input.substring(voice_input.indexOf("开") + 1,voice_input.indexOf("到"));
                String code=voice_input.substring(voice_input.indexOf("到")+1);
                deviceManagementService.operateDevice(time,code,device);
                analyzeRepository.analyze(device,code);
                DeviceVO deviceVO=new DeviceVO();
                deviceVO.setId(device);
                deviceVO.setState(code);
                return deviceVO;
            }
            else {
                device= voice_input.substring(voice_input.indexOf("开") + 1);
                String code="1";
                deviceManagementService.operateDevice(time,code,device);
                analyzeRepository.analyze(device,code);
                DeviceVO deviceVO=new DeviceVO();
                deviceVO.setId(device);
                deviceVO.setState(code);
                return deviceVO;
            }

        }
        else if(voice_input.contains("关")&&!voice_input.contains("模式")){
            String device=voice_input.substring(voice_input.indexOf("关")+1);
            return offDevice(device,id,time);
        }

        else {
            return null;
        }


    }



    private DeviceVO offDevice(String device,int id,String time){
        //调用关闭设备接口
        String code="0";
        deviceManagementService.operateDevice(time,code,device);
        analyzeRepository.analyze(device,code);
        DeviceVO deviceVO=new DeviceVO();
        deviceVO.setState(code);
        deviceVO.setId(device);
        return deviceVO;
    }



}

package com.example.iot.service;

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

    @Override
    public DeviceVO analyzeVoice(String voice_input,String username,String time) {

        String sql="SELECT id from user where username='"+username+"'";
        List<String> user=jdbcTemplate.queryForList(sql,String.class);

        int id=Integer.valueOf(user.get(0));


        if(voice_input.contains("开")&&!voice_input.contains("模式")){

            String device=voice_input.substring(voice_input.indexOf("开")+1);
            return onDevice(device,id,time);
        }
        else if(voice_input.contains("关")&&!voice_input.contains("模式")){
            String device=voice_input.substring(voice_input.indexOf("关")+1);
            return offDevice(device,id,time);
        }
        else if(voice_input.contains("开")&&voice_input.contains("模式")){

            String mode=voice_input.substring(voice_input.indexOf("开")+1);
            //打开模式
            onMode(mode,id,time);
            return null;
        }
        else if(voice_input.contains("关")&&voice_input.contains("模式")){

            String mode=voice_input.substring(voice_input.indexOf("关")+1);
            //关闭模式
            offMode(mode,id,time);
            return null;
        }
        else {
            return null;
        }


    }

    private DeviceVO onDevice(String device, int id, String time){


        //调用打开设备接口

        String code="1";
        deviceManagementService.operateDevice(time,code,device);

        DeviceVO deviceVO=new DeviceVO();
        deviceVO.setId(device);
        deviceVO.setState(code);
        return deviceVO;

    }

    private DeviceVO offDevice(String device,int id,String time){
        //调用关闭设备接口
        String code="0";
        deviceManagementService.operateDevice(time,code,device);
        DeviceVO deviceVO=new DeviceVO();
        deviceVO.setState(code);
        deviceVO.setId(device);
        return deviceVO;
    }

    private void onMode(String mode,int id,String time){
        //调用打开模式接口
        if(mode.contains("影音")){
            //打开电视，关闭窗帘，关闭电灯

        }
        else {
            //关闭电灯，窗帘，电视，打开空调
        }
    }

    private void offMode(String mode,int id,String time){
        //调用关闭模式接口
        if(mode.contains("影音")){
            //关闭电视，打开窗帘，打开电灯
        }
        else {
            //打开电灯，窗帘，电视，关闭空调
        }
    }

}

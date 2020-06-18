package com.example.iot.service;

import com.example.iot.service.Voice.AnalyzeVoice;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceService implements AnalyzeVoice {

    @Override
    public void analyzeVoice(String voice_input,int id) {
        if(voice_input.contains("开")&&!voice_input.contains("模式")){

            String device=voice_input.substring(voice_input.indexOf("开")+1);
            onDevice(device,id);
        }
        else if(voice_input.contains("关")&&!voice_input.contains("模式")){
            String device=voice_input.substring(voice_input.indexOf("关")+1);
            offDevice(device,id);
        }
        else if(voice_input.contains("开")&&voice_input.contains("模式")){

            String mode=voice_input.substring(voice_input.indexOf("开")+1);
            //打开模式
            onMode(mode,id);
        }
        else if(voice_input.contains("关")&&voice_input.contains("模式")){

            String mode=voice_input.substring(voice_input.indexOf("关")+1);
            //关闭模式
            offMode(mode,id);
        }


    }

    private void onDevice(String device,int id){
        //调用打开设备接口
        System.out.println("打开设备 "+device);
    }

    private void offDevice(String device,int id){
        //调用关闭设备接口
    }

    private void onMode(String mode,int id){
        //调用打开模式接口
        if(mode.contains("影音")){
            //打开电视，关闭窗帘，关闭电灯
        }
        else {
            //关闭电灯，窗帘，电视，打开空调
        }
    }

    private void offMode(String mode,int id){
        //调用关闭模式接口
        if(mode.contains("影音")){
            //关闭电视，打开窗帘，打开电灯
        }
        else {
            //打开电灯，窗帘，电视，关闭空调
        }
    }

}

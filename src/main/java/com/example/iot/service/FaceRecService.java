package com.example.iot.service;

import com.example.iot.po.User.Device;
import com.example.iot.service.Recognition.FaceRecognition;
import com.example.iot.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FaceRecService implements FaceRecognition {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    AutoOperateService autoOperateService;


    @Override
    public List<DeviceVO> recognition_string(String input, String username, String timeInterval) {
        //出家门情况
        if(input=="0"){
            //调用关闭空调，电视机，电灯，音箱，加湿器
        }

        //进家门情况
        else if(input=="1"){
            String sql="SELECT id from user where username='"+username+"'";
            List<String> user=jdbcTemplate.queryForList(sql,String.class);

            int id=Integer.valueOf(user.get(0));

            //根据时间段判断是否打开电灯、窗帘，根据温度判断是否开空调，根据湿度判断是否开加湿器。
            sql="SELECT time FROM environment where id=\""+id+"\";";
            List<String> time=jdbcTemplate.queryForList(sql,String.class);



            sql="SELECT temperature FROM environment where id=\""+id+"\";";
            List<String> temp=jdbcTemplate.queryForList(sql,String.class);


            sql="SELECT humidity FROM environment where id=\""+id+"\";";
            List<String> hum=jdbcTemplate.queryForList(sql,String.class);

            autoOperateService.autoOperate(username,String.valueOf(time.get(0)),String.valueOf(temp.get(0)),String.valueOf(hum.get(0)),"1",timeInterval);
        }
        return null;
    }


}

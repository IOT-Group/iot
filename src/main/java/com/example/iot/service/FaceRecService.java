package com.example.iot.service;

import com.example.iot.dao.Repository.DeviceManagementRepository;
import com.example.iot.dao.Repository.EnvironmentRepository;
import com.example.iot.dao.Repository.UserRepository;
import com.example.iot.po.User.Device;
import com.example.iot.po.User.Environment;
import com.example.iot.po.User.EnvironmentMapper;
import com.example.iot.po.User.HomeCondition;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnvironmentRepository environmentRepository;


    @Autowired
    DeviceManagementRepository deviceManagementRepository;

    @Override
    public HomeCondition recognition_string(String input, String username, String timeInterval,String time) {
        //出家门情况
        if(input.equals("0")){


            String sql="SELECT id from user where username='"+username+"'";
            List<String> user=jdbcTemplate.queryForList(sql,String.class);

            int id=Integer.valueOf(user.get(0));
            environmentRepository.changeHome(id,0);

            sql="SELECT id from device where userId='"+id+"'";
            List<String> devices=jdbcTemplate.queryForList(sql,String.class);

            //调用关闭空调，电视机，电灯，音箱，加湿器
            for(int i=0;i<devices.size();i++) {
                deviceManagementRepository.operateDevice(time, "0", devices.get(i));
            }

            Environment environment=jdbcTemplate.queryForObject(" select time,temperature,humidity,ownerState from environment,(select id from user where username=?)T where environment.userid=T.id;",new EnvironmentMapper(),username);
            List<Device> devicesList=userRepository.getUserDevice(username);
            return  new HomeCondition(environment,devicesList);

        }

        //进家门情况
        else {
            String sql="SELECT id from user where username='"+username+"'";
            List<String> user=jdbcTemplate.queryForList(sql,String.class);

            int id=Integer.valueOf(user.get(0));

            //根据时间段判断是否打开电灯、窗帘，根据温度判断是否开空调，根据湿度判断是否开加湿器。


            environmentRepository.changeHome(id,1);

            sql="SELECT temperature FROM environment where userid=\""+id+"\";";
            List<String> temp=jdbcTemplate.queryForList(sql,String.class);


            sql="SELECT humidity FROM environment where userid=\""+id+"\";";
            List<String> hum=jdbcTemplate.queryForList(sql,String.class);

            autoOperateService.autoOperate(username,time,String.valueOf(temp.get(0)),String.valueOf(hum.get(0)),"1",timeInterval);

            Environment environment=jdbcTemplate.queryForObject(" select time,temperature,humidity,ownerState from environment,(select id from user where username=?)T where environment.userid=T.id;",new EnvironmentMapper(),username);
            List<Device> devicesList=userRepository.getUserDevice(username);
            return  new HomeCondition(environment,devicesList);
        }

    }


}

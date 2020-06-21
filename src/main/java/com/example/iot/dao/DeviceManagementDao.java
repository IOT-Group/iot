package com.example.iot.dao;

import com.example.iot.dao.Repository.DeviceManagementRepository;
import com.example.iot.vo.AddDeviceResponse;
import com.example.iot.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.iot.po.devices.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceManagementDao implements DeviceManagementRepository {
    int latesttime=0;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    List<device>runningdevices=new ArrayList<>();
    List<AirConditioner> airConditioners = new ArrayList<>();
    List<Light>lights=new ArrayList<>();
    List<TV>tvs=new ArrayList<>();
    List<Curtain>curtains=new ArrayList<>();
    List<Box>boxes=new ArrayList<>();
    List<Humidifier>humidifiers=new ArrayList<>();


    @Override
    public boolean initialize(){
        runningdevices=jdbcTemplate.query("select id , state from device where state !='0';",new deviceMapper());

        for (int i=0;i<runningdevices.size();i++) {
            int id=runningdevices.get(i).getId();
            String type="";
            try{
                type=jdbcTemplate.queryForObject("select type from device where id= ?",String.class,id);
            }catch (Exception e){
                System.out.println("没有正在运行的设备");
            }
            assert type != null;
            if(type.startsWith("A")){
                airConditioners.add(new AirConditioner(String.valueOf(runningdevices.get(i).getState()),String.valueOf(runningdevices.get(i).getState())));
            }

            else if(type.startsWith("L")) {
                lights.add(new Light(String.valueOf(runningdevices.get(i).getState()),String.valueOf(runningdevices.get(i).getState())));
            }
            else if(type.startsWith("C")) {
                curtains.add(new Curtain(String.valueOf(runningdevices.get(i).getState()),String.valueOf(runningdevices.get(i).getState())));
            }
            else if(type.startsWith("H")) {
                humidifiers.add(new Humidifier(String.valueOf(runningdevices.get(i).getState()),String.valueOf(runningdevices.get(i).getState())));
            }
            else if(type.startsWith("T")) {
                tvs.add(new TV(String.valueOf(runningdevices.get(i).getState()),String.valueOf(runningdevices.get(i).getState())));
            }
            else if(type.startsWith("B")) {
                boxes.add(new Box(String.valueOf(runningdevices.get(i).getState()),String.valueOf(runningdevices.get(i).getState())));
            }
        }
        return true;
    }
    @Override
    public AddDeviceResponse addDevice(String type, String owner) {
        AddDeviceResponse adr=new AddDeviceResponse();
        String userid=jdbcTemplate.queryForObject("select id from user where username = ?",String.class,owner);
        jdbcTemplate.update("insert into device (`type`,`userId`,`state`) values (?,?,?)",type,userid,"0");
        int id=jdbcTemplate.queryForObject("select max(id) from device",Integer.class);
        adr.setId(String.valueOf(id));
        adr.setTypeid(type);
        return adr;
    }

    @Override
    public boolean deleteDevice(String deviceId) {
        int id=Integer.parseInt(deviceId);
        jdbcTemplate.update("delete from device where id=\""+ id + "\"");
        return true;
    }

    @Override
    public boolean operateDevice(String time, String code, String deviceId) {
        //判断是否有设备是要自动操控，若是，则不把操控的记录放入operation表里
        boolean isAutoOperation=false;
        if((!code.equals(""))&&code.substring(0,1).equals("A")){
            isAutoOperation=true;
            code=code.substring(1);
        }
        String oldState="";
        int now=Integer.parseInt(time);
        int gap=now-latesttime;         //上次操作距这次操作的时间，用于决定设备运行的效果
        latesttime=now;
        int flag=0;       //默认运行设备列表中无当前操作设备
        //if(gap>0&&!deviceId.equals("")) {
        if(gap>0){
            if(airConditioners.size()!=0)
                for (com.example.iot.po.devices.AirConditioner a : airConditioners) {
                    if (a.getState()!= 0) {
                        a.update(gap);
                    }
                    if(a.getId()==Integer.parseInt(deviceId)){
                        a.setState(Integer.parseInt(code));
                        flag=1;
                        int id=Integer.parseInt(deviceId);
                        oldState=jdbcTemplate.queryForObject("select state from device where id= ?",String.class,id);
                        jdbcTemplate.update("update device set state =? where id= ?",code,id);
                    }
                }
            if(lights.size()!=0)
                for (com.example.iot.po.devices.Light a : lights) {
                    if (a.getState()!= 0) {
                        a.update(gap);
                    }
                    if(a.getId()==Integer.parseInt(deviceId)){
                        a.setState(Integer.parseInt(code));
                        flag=1;
                        int id=Integer.parseInt(deviceId);
                        oldState=jdbcTemplate.queryForObject("select state from device where id= ?",String.class,id);
                        jdbcTemplate.update("update device set state =? where id= ?",code,id);
                    }
                }
            if(curtains.size()!=0)
                for (com.example.iot.po.devices.Curtain a : curtains) {
                    if (a.getState()!= 0) {
                        a.update(gap);
                    }
                    if(a.getId()==Integer.parseInt(deviceId)){
                        a.setState(Integer.parseInt(code));
                        flag=1;
                        int id=Integer.parseInt(deviceId);
                        oldState=jdbcTemplate.queryForObject("select state from device where id= ?",String.class,id);
                        jdbcTemplate.update("update device set state =? where id= ?",code,id);
                    }
                }
            if(humidifiers.size()!=0)
                for (com.example.iot.po.devices.Humidifier a : humidifiers) {
                    if (a.getState()!= 0) {
                        a.update(gap);
                    }
                    if(a.getId()==Integer.parseInt(deviceId)){
                        a.setState(Integer.parseInt(code));
                        flag=1;
                        int id=Integer.parseInt(deviceId);
                        oldState=jdbcTemplate.queryForObject("select state from device where id= ?",String.class,id);
                        jdbcTemplate.update("update device set state =? where id= ?",code,id);
                    }
                }
            if(tvs.size()!=0)
                for (com.example.iot.po.devices.TV a : tvs) {
                    if (a.getState()!= 0) {
                        a.update(gap);
                    }
                    if(a.getId()==Integer.parseInt(deviceId)){
                        a.setState(Integer.parseInt(code));
                        flag=1;
                        int id=Integer.parseInt(deviceId);
                        oldState=jdbcTemplate.queryForObject("select state from device where id= ?",String.class,id);
                        jdbcTemplate.update("update device set state =? where id= ?",code,id);
                    }
                }
            if(boxes.size()!=0)
                for (com.example.iot.po.devices.Box a : boxes) {
                    if (a.getState()!= 0) {
                        a.update(gap);
                    }
                    if(a.getId()==Integer.parseInt(deviceId)){
                        a.setState(Integer.parseInt(code));
                        flag=1;
                        int id=Integer.parseInt(deviceId);
                        oldState=jdbcTemplate.queryForObject("select state from device where id= ?",String.class,id);
                        jdbcTemplate.update("update device set state =? where id= ?",code,id);
                    }
                }
        }        //各类设备更新
        //if(!deviceId.equals("")) {
            if (flag == 0) {
                int id = Integer.parseInt(deviceId);
                String type = jdbcTemplate.queryForObject("select type from device where id= ?", String.class, id);
                assert type != null;
                if (type.startsWith("A")) {
                    airConditioners.add(new AirConditioner(code, deviceId));
                } else if (type.startsWith("L")) {
                    lights.add(new Light(code, deviceId));
                } else if (type.startsWith("C")) {
                    curtains.add(new Curtain(code, deviceId));
                } else if (type.startsWith("H")) {
                    humidifiers.add(new Humidifier(code, deviceId));
                } else if (type.startsWith("T")) {
                    tvs.add(new TV(code, deviceId));
                } else if (type.startsWith("B")) {
                    boxes.add(new Box(code, deviceId));
                }
                oldState = jdbcTemplate.queryForObject("select state from device where id= ?", String.class, id);
                jdbcTemplate.update("update device set state =? where id= ?", code, id);
            }
            int userid = Integer.parseInt(jdbcTemplate.queryForObject("select userId from device where id=?", String.class, deviceId));

            String temperature = jdbcTemplate.queryForObject("select temperature from environment where userid=?", String.class, userid);
            String humidity = jdbcTemplate.queryForObject("select humidity from environment where userid=?", String.class, userid);

            if (!oldState.equals(code) && !isAutoOperation) {
                jdbcTemplate.update("insert into operation (deviceid,`time`,code,temperature,humidity)value (?,?,?,?,?)", deviceId, time, code, temperature, humidity);
            }
      //  }
        return true;
    }
}

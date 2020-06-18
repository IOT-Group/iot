package com.example.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.iot.dao","com.example.iot.config",
        "com.example.iot.controller","com.example.iot.service"})
public class IotApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }

}

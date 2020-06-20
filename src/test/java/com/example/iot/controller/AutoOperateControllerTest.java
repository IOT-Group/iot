package com.example.iot.controller;

import com.example.iot.vo.Environment;
import org.json.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AutoOperateControllerTest {
    @Autowired
    AutoOperateController autoOperateController;
    @Autowired
    private MockMvc mvc;

    @Test
    public void autoOperateTest() throws Exception{
        Environment environment=new Environment("benson","400","35","40","1","5");
        String result = mvc.perform(MockMvcRequestBuilders.post("/api/sendEnvironment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertNotNull(result);
    }

}

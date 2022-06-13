//package com.example.cafemanagementsystem;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.awt.*;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebAppConfiguration
//@SpringBootTest
//class CafeManagementSystemApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    private  void setUp() throws  Exception{
//        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//    }
//
//    @Test
//    public  void createUserTest() throws Exception {
//        String payload=" { \"firstName\":\"user\",\n" +
//                "       \"lastName\": \"user\",\n" +
//                "       \"username\": \"user\",\n" +
//                "        \"active\":\"true\",\n" +
//                "    \"userRoleType\": \"ADMIN\"}";
//        mockMvc.perform(post("/api/auth")
//                .contentType(MediaType.APPLICATION_JSON).
//                content(payload)).andExpect(status().isOk()).andReturn();
//    }
//
//    @Test
//    public  void fetchUserTest() throws Exception{
//        mockMvc.perform(get("/signUp",1L))
//                .andExpect(status().isOk()).andReturn();
//    }
//}

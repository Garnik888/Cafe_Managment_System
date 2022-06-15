package com.example.cafemanagementsystem;

import com.example.cafemanagementsystem.domain.enums.RoleType;
import com.example.cafemanagementsystem.dto.request.SignInRequestDto;
import com.example.cafemanagementsystem.dto.request.SignUpRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;


@SpringBootTest(classes = CafeManagementSystemApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @Transactional
    @WithMockUser(username = "admin", authorities = "ADMIN")
    public void SignInTest() throws Exception {
        SignInRequestDto signInRequestDto = new SignInRequestDto();
        signInRequestDto.setUsername("Manager1");
        signInRequestDto.setPassword("Manager1");
        String user = objectMapper.writeValueAsString(signInRequestDto);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/auth/signIn")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(user.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", authorities = "ADMIN")
    public void SignUpTest() throws Exception {

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setFirstName("WAITER5");
        signUpRequestDto.setLastName("WAITER5");
        signUpRequestDto.setUsername("USER");
        signUpRequestDto.setUserRoleType(RoleType.WAITER);
        signUpRequestDto.setPassword(passwordEncoder.encode("PASS1"));


        String userJson = objectMapper.writeValueAsString(signUpRequestDto);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/auth/signUp")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}




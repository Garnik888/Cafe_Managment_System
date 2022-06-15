package com.example.cafemanagementsystem;

import com.example.cafemanagementsystem.domain.enums.AssortmentType;
import com.example.cafemanagementsystem.dto.request.AssortmentRequestDto;
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
public class AssortmentControllerTest {


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
    @WithMockUser(username = "Manager1", authorities = "MANAGER")
    public void addAssortmentTest() throws Exception {
        AssortmentRequestDto assortmentRequestDto = new AssortmentRequestDto();
        assortmentRequestDto.setName("PIZZA");
        assortmentRequestDto.setPrice(1500.00);
        assortmentRequestDto.setAssortmentType(AssortmentType.FOOD);

        String userJson = objectMapper.writeValueAsString(assortmentRequestDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/assortment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "Waiter1", authorities = "WAITER")
    public void getFood() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/assortment/food")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "Waiter1", authorities = "WAITER")
    public void getDrink() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/assortment/drink")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

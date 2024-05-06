package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostAddress() throws Exception {
        String requestBody = "{\"residence\":\"123 Main St\",\"zipCode\":\"12345-678\",\"number\":\"456\",\"city\":\"Anytown\",\"state\":\"NY\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/address/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testPostFavoriteAddress() throws Exception {
        String requestBody = "{\"residence\":\"456 Elm St\",\"zipCode\":\"98765-432\",\"number\":\"789\",\"city\":\"Othertown\",\"state\":\"CA\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/address/1/favorite")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

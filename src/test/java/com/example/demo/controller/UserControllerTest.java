package com.example.demo.controller;


import static org.mockito.Mockito.when;

import java.util.Collections;

import com.example.demo.controller.V1.UserController;
import com.example.demo.entities.UserEntity;
import com.example.demo.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetOneUser() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFullName("John Doe");
        user.setBirthDate("22/04/1999");
        when(userService.getUser(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("fullName").value("John Doe"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFullName("John Doe");
        user.setBirthDate("22/04/1999");
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("John Doe"));
    }

    @Test
    public void testPostUserWithoutFullName() throws Exception {
        UserEntity userWithoutFullName = new UserEntity();
        userWithoutFullName.setBirthDate("22/04/1999");
        userWithoutFullName.setId(1L);
    
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userWithoutFullName)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPostUserWithWrongBirthDate() throws Exception{
        UserEntity userWithWrongBirthDate = new UserEntity();
        userWithWrongBirthDate.setFullName("John Doe");
        userWithWrongBirthDate.setBirthDate("220330243023092");
        userWithWrongBirthDate.setId(1L);

        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userWithWrongBirthDate)))
            .andExpect(status().isBadRequest());

        
    }   
}

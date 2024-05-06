package com.example.demo.service.userService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;

public class SaveUserService {
    
 @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        UserEntity userToSave = new UserEntity();
        userToSave.setFullName("John Doe");

        Mockito.when(userRepository.save(userToSave)).thenReturn(userToSave);

        UserEntity savedUser = userService.saveUser(userToSave);

        Assertions.assertEquals("John Doe", savedUser.getFullName());
    }
}

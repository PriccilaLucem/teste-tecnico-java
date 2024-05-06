package com.example.demo.service.userService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserFound() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserEntity result = userService.getUser(1L);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testGetUserNotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            userService.getUser(1L);
        });
    }
}

package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired 
    UserRepository userRepository;

    public UserEntity getUser(long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public List<UserEntity> getAlUsers(){
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }
}

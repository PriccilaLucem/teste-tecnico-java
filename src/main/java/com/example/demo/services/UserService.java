package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AdressessEntity;
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
    public UserEntity saveAddressOnUser(Long id, AdressessEntity address){
        UserEntity user = this.getUser(id);
        user.setAdressess(address);
        return userRepository.save(user);
    }

    public UserEntity saveUserFavoriteAddress(Long id, AdressessEntity address){
        UserEntity user = this.getUser(id);
        user.setAdressess(address);
        user.setFavoriteAddress(address);
        user.setFavoriteAddress(address);
        userRepository.save(user);
        return user;
    }
}

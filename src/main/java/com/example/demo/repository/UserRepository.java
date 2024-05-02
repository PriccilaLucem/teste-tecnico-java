package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    
}
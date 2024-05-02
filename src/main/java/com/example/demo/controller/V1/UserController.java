package com.example.demo.controller.V1;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.UserEntity;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;


    @GetMapping("/{user_id}")
    public  ResponseEntity<?> getOneUser(@PathVariable(value = "user_id") Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    @GetMapping
    public  ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAlUsers());
    }
    
    @PostMapping
    public ResponseEntity<?> postUserMapping(HttpServletRequest request,@RequestBody UserEntity userEntity) {
        
        
        URI uri = URI.create(request.getRequestURI());
        return ResponseEntity.created(uri).body(userService.saveUser(userEntity));
    }
    

}

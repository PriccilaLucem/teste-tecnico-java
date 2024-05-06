package com.example.demo.controller.V1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AdressessEntity;
import com.example.demo.services.AdressService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    
    @Autowired
    AdressService adressService;

    @Autowired
    UserService userService;

    @PostMapping("/{user_id}")
    public ResponseEntity<?> postAddress(HttpServletRequest request, @RequestBody AdressessEntity entity,
    @PathVariable(value = "user_id") Long id) {
        
        URI uri = URI.create(request.getRequestURI());
        AdressessEntity address = adressService.createOrFindNewAddress(entity);
        
        return ResponseEntity.created(uri).body(userService.saveAddressOnUser(id, address));
    }
    
    @PostMapping("/{user_id}/favorite")
    public ResponseEntity<?> postFavoriteAddress(HttpServletRequest request, @RequestBody AdressessEntity entity,
    @PathVariable(value = "user_id") Long id) {
        AdressessEntity address = adressService.createOrFindNewAddress(entity);
        
        return ResponseEntity.ok().body(userService.saveUserFavoriteAddress(id, address));
    }
    
    
}

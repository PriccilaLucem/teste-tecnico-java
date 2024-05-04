package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AdressessEntity;
import com.example.demo.repository.AdressRepository;

@Service
public class AdressService {
    

    @Autowired
    AdressRepository adressRepository;



    public AdressessEntity createOrFindNewAddress(AdressessEntity address){
        
        return adressRepository.getOrCreate(address);
    }

    
}

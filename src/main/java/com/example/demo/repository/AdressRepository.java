package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.AdressessEntity;

public interface AdressRepository extends JpaRepository<AdressessEntity,Long>{
    
    public Optional<AdressessEntity> findByResidenceAndZipCodeAndNumberAndCityAndState(String residence, String zipCode, String number, String city, String state);

    default AdressessEntity getOrCreate(AdressessEntity addressEntity){
        Optional<AdressessEntity>  address = findByResidenceAndZipCodeAndNumberAndCityAndState(addressEntity.getResidence(), addressEntity.getZipCode(), addressEntity.getNumber(), addressEntity.getCity(), addressEntity.getState());   

        return address.orElseGet(() -> save(addressEntity));

    } 
}




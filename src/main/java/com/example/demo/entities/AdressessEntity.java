package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressess")
public class AdressessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255, name="adress")
    private String address;
    
    
    @Column(nullable = false, length = 9, name="zip_code")
    private String zipCode;
    
    @Column(nullable = false, length = 8, name="number")
    private String number;
    
    @Column(nullable = false, length = 255, name="city")
    private String city;
    
    @Column(nullable = false, length = 2, name="state")
    private String state;

}

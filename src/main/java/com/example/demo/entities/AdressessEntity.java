package com.example.demo.entities;

import com.example.demo.exceptions.InvalidDataException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;



@Entity
@Table(name = "addresses")
public class AdressessEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255, name="residence")
    private String residence;
    
    
    @Column(nullable = false, length = 9, name="zip_code")
    private String zipCode;
    
    @Column(nullable = false, length = 8, name="number")
    private String number;
    
    @Column(nullable = false, length = 255, name="city")
    private String city;
    
    @Column(nullable = false, length = 2, name="state")
    private String state;

    public AdressessEntity(){}

    public AdressessEntity(Long id, String residence, String zipCode, String number, String city, String state){
        this.id = id;
        this.residence = residence;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
        this.state = state;
    }

    @PrePersist
    public void ValidateData(){
        String regex = "^\\d{5}-\\d{3}$";
        if(!this.zipCode.matches(regex)){
            throw new IllegalArgumentException("Invalid zip-code");
        }
        if(this.city == null || this.number == null || this.residence == null || this.state == null){
            throw new InvalidDataException("Data is missing");
        }
    }

    
    public String getCity() {
        return city;
    }
    public long getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public String getResidence() {
        return residence;
    }
    public String getState() {
        return state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setResidence(String residence) {
        this.residence = residence;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
}

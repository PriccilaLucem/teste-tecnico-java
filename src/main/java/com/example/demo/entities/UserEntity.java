package com.example.demo.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50, name = "full_name")
    private String fullName;

    @Column(nullable = false, length = 10, name = "birth_date")
    private String birthDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_addresses", 
        joinColumns = @JoinColumn(name="users_id"),
        inverseJoinColumns = @JoinColumn(name="addresses_id"))
    Set<AdressessEntity> adressess = new HashSet<>();
    
@   OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_address_id")
    private AdressessEntity favoriteAddress;

    public UserEntity(){

    }
    
    public UserEntity(Long id, String fullName, String birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }
    
    @PrePersist
    public void ValidateData(){
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        if(!this.birthDate.matches(regex)){
            throw new IllegalArgumentException("Invalid date format");
        }
    }
    public Set<AdressessEntity> getAdressess() {
        return adressess;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public String getFullName() {
        return fullName;
    }
    public long getId() {
        return id;
    }
    public AdressessEntity getFavoriteAddress() {
        return favoriteAddress;
    }
    

    public void setFavoriteAddress(AdressessEntity favoriteAddress) {
        this.favoriteAddress = favoriteAddress;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public void setAdressess(AdressessEntity address) {
        this.adressess.add(address);
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

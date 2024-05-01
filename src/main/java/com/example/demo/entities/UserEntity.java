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
    @JoinTable(name = "users_adressess", joinColumns = @JoinColumn(name="adressess_id"),
    inverseJoinColumns = @JoinColumn(name="users_id"))
    Set<AdressessEntity> adressess = new HashSet<>();


}

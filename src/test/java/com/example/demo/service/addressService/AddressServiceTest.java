package com.example.demo.service.addressService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entities.AdressessEntity;
import com.example.demo.repository.AdressRepository;
import com.example.demo.services.AdressService;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AdressRepository adressRepository;

    @InjectMocks
    private AdressService adressService;

    @Test
    public void testCreateOrFindNewAddress() {
        AdressessEntity address = new AdressessEntity();
        address.setResidence("123 Main St");
        address.setCity("IDK");
        address.setNumber("123");
        address.setState("SP");
        address.setZipCode("12345-789");

        Mockito.when(adressRepository.getOrCreate(address)).thenReturn(address);

        AdressessEntity result = adressService.createOrFindNewAddress(address);

        Assertions.assertEquals(address, result);
    }
}

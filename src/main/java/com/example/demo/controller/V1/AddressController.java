package com.example.demo.controller.V1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AdressessEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.services.AdressService;
import com.example.demo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/address")
@Tag(name = "Address", description = "Address routes")
public class AddressController {
    
    @Autowired
    AdressService adressService;

    @Autowired
    UserService userService;

    @Operation(responses = {
        @ApiResponse(description = "Ok", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(description = "Bad request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class))),
        @ApiResponse(description = "Not found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class))),
    })
    @PostMapping("/{user_id}")
    public ResponseEntity<?> postAddress(HttpServletRequest request, @RequestBody AdressessEntity entity,
    @PathVariable(value = "user_id") Long id) {
        
        URI uri = URI.create(request.getRequestURI());
        AdressessEntity address = adressService.createOrFindNewAddress(entity);
        
        return ResponseEntity.created(uri).body(userService.saveAddressOnUser(id, address));
    }
    @Operation(responses = {
        @ApiResponse(description = "Ok", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(description = "Bad request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class))),
    })
    @PostMapping("/{user_id}/favorite")
    public ResponseEntity<?> postFavoriteAddress(HttpServletRequest request, @RequestBody AdressessEntity entity,
    @PathVariable(value = "user_id") Long id) {
        AdressessEntity address = adressService.createOrFindNewAddress(entity);
        
        return ResponseEntity.ok().body(userService.saveUserFavoriteAddress(id, address));
    }
    
    
}

package com.example.demo.controller.V1;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(responses = {
        @ApiResponse(description = "Ok", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))),
    })
    @GetMapping("/{user_id}")
    public  ResponseEntity<?> getOneUser(@PathVariable(value = "user_id") Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @Operation(responses = {
        @ApiResponse(description = "Ok", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(description = "NotFound", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class))),
    })
    @GetMapping
    public  ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    
    @Operation(responses = {
        @ApiResponse(description = "Ok", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(description = "Bad request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseException.class))),
    })
    @PostMapping
    public ResponseEntity<?> postUserMapping(HttpServletRequest request,@RequestBody UserEntity userEntity) {
        
        
        URI uri = URI.create(request.getRequestURI());
        return ResponseEntity.created(uri).body(userService.saveUser(userEntity));
    }
    

}

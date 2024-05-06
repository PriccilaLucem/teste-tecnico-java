package com.example.demo.exceptions;

public class ErrorResponse extends RuntimeException{
    public ErrorResponse(String msg){
        super(msg);
    }
}

package com.hitruong.RestAPI.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    private String error;

    public CustomException(String error, String message) {
        super(message);
        this.error = error;
    }
}

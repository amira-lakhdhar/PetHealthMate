package com.example.userms.validation;

public class EmailExistsException extends Exception{
    public EmailExistsException(String message) {
        super(message);
    }
}

package com.example.userms.validation;

public class InvalidPasswordException extends Throwable {
    public InvalidPasswordException(String invalidPassword) {
        super(invalidPassword);

    }
}

package com.example.user.Exceptions;

public class EmailNotUniqueException extends RuntimeException{
    public EmailNotUniqueException(String message) {
        super(message);
    }
}

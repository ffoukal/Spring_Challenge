package com.example.cart.Exceptions;

public class ItemNotFoundInCartException extends RuntimeException{
    public ItemNotFoundInCartException(String message) {
        super(message);
    }
}

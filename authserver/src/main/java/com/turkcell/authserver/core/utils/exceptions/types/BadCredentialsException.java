package com.turkcell.authserver.core.utils.exceptions.types;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String message) {
        super(message);
    }
}
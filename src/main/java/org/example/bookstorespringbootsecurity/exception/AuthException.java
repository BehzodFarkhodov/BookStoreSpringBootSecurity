package org.example.bookstorespringbootsecurity.exception;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}

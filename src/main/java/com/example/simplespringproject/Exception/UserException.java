package com.example.simplespringproject.Exception;

public class UserException extends RuntimeException {
    public UserException(Integer id) {
        super("User not found with id: " + id);
    }
}
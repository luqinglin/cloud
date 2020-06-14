package me.sta.user.exception;

public class UserLoginException extends RuntimeException {
    public UserLoginException(String message) {
        super(message);
    }
}
package com.ticket.bookmyshow.exceptions;

//extends Exception since it is checkedException
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }

}

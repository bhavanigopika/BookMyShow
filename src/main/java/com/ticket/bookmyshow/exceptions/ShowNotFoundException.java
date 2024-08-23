package com.ticket.bookmyshow.exceptions;

//extends Exception since it is checkedException
public class ShowNotFoundException extends Exception{
    public ShowNotFoundException(String message){
        super(message);
    }
}

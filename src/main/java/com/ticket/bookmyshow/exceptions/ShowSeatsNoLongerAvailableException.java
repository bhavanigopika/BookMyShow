package com.ticket.bookmyshow.exceptions;

public class ShowSeatsNoLongerAvailableException extends Exception{
    public ShowSeatsNoLongerAvailableException(String message){
        super(message);
    }
}

package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Booking extends BaseModel{
    @ManyToOne
    private User user;
    private int amount;
    // if 6 seats  are allocated for 1 booking/ticket,of course same seat available in different screen(shows) also
    // but I am booking the list of seat for the particular show, so we need list<ShowSeat>
    @ManyToMany
    private List<ShowSeat> showSeatList;
    @OneToMany
    private List<Payment> paymentList;
    //EnumType.ORDINAL - automatically assign the integer values from 0 onwards
    @Enumerated(EnumType.ORDINAL) /*since it is enum*/
    private BookingStatus bookingStatus;
}
/*
Booking : User
     1  :  1
     m  :  1
     -------
     m  :  1

Booking : ShowSeat
     1  :  m  1 booking have multiple show seat obviously
     m  :  1  if anybody cancelled the booking then same showSeat be assigned to some other booking. So, 1 ShowSeat have multiple booking
     -------
     m  :  m

Booking : Payment
     1  :  m
     1  :  1
     -------
     1  :  m
How 1 : m for booking : payment?

    Say, booking amount is Rs.1000
    we can pay 1 payment through UPI - 200
       1 payment through credit card - 300
         other payment by net baning - 500
 */

package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeatType extends BaseModel {
    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
    private int price;
}

/*
Why ShowSeatType mapping?

Show : SeatType
  1  :  m  = 1 show have different seatType such as CLASSIC, PRIME, GOLD
  m  :  1  = 1 seatType(say, CLASSIC) have in multiple shows
----------------
  m  :  m
This mapping is needed for price because to avoid redundant data
Otherwise, we can use price in ShowSeat

Cardinality:

particular show, particular seat means happening in that particular show and seat so, happening in particular screen(hall).
but, at the end of the day different shows play in different screens....

ShowSeatType  :  Show
         1    :    1 = one particular show and one particular seatType have only one show
         m    :    1 = one show have multiple show and multiple seatType
         -----------
         m    :    1

ShowSeatType  :  SeatType
           1  :  1 = one particular show and one particular seatType have only one seatType
           m  :  1 = one seatType have multiple show and multiple seatType
           -------
           m  :  1

Important Point:

Say, we have class A and class B
cardinality between AB : A (or) AB : B, is always m : 1

Example:
Show       SeatType    price
 X         CLASSIC      200
 X         PRIME        300
 X         DIAMOND      300
 X         GOLD         500

 Y         GOLD         500
 Y         DIAMOND      600
 Y         CLASSIC      200

 Z         PLATINUM     900

ShowSeatType : Show
---------------
Say, X PRIME ShowSeatType have 1 show that is X show (X belong to X show only)
Say, Y GOLD ShowSeatType have 1 show
Say, Z PLATINUM ShowSeatType have 1 show
So, 1 ShowSeatType have 1 Show(1 : 1)
----------
1 show(say, X) have multiple ShowSeatType (i.e) X CLASSIC, Y PRIME, Z PLATINUM
1 show(say, Y) have multiple ShowSeatType (i.e) Y GOLD, Y DIAMOND, Y CLASSIC
1 show(say, Z) have multiple ShowSeatType (i.e) Z PLATINUM
So, 1 Show have multiple ShowSeatType(m : 1)

ShowSeatType : Seat
---------------
Say, X PRIME ShowSeatType have 1 seat
Say, Y CLASSIC ShowSeatType have 1 seat
Say, Z PLATINUM ShowSeatType gave 1 seat
So, 1 ShowSeatType have 1 Seat(1 : 1)
----------
1 seatType(say, CLASSIC) have multiple ShowSeatType (i.e) X CLASSIC, Y CLASSIC
1 seatType(say, PLATINUM) have multiple ShowSeatType (i.e) Z PLATINUM
1 seatType(say, DIAMOND) have multiple ShowSeatType (i.e) X DIAMOND, Y DIAMOND
So, 1 seat have multiple ShowSeatType(m : 1)
 */


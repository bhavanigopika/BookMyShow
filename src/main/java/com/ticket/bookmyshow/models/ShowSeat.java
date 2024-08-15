package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;
}
/*
Why ShowSeat mapping class?
show : seat
1 : m
m : 1 because same seat available in different show also...show is responsible for movie, screen, feature, location, theatre, time and all...
-----
m : m -> So, we have created showSeat mapping classclass

We added status of showSeat here because, in Seat class, if status mentioned then it represent for the particular seatNumber only
But same seatNo exist in every show also...

In ShowSeat class -> we mentioned status with Show and Seat also -> it means that this seat is exist for this particular show or not...
 */

/*
cardinality:

particular show, particular seat means happening in that particular show and seat so, happening in particular screen(hall).
but, at the end of the day different shows play in different screens....

ShowSeat : Show
      1  :  1 = one particular show and one particular seat have only one show like show : show = 1 : 1
      m  :  1 = only one show will have multiple show and multiple seat
      -------
      m  :  1
ShowSeat : Seat
      1  :  1 = one particular show and one particular seat have only one seat
      m  :  1 = only one seat will have multiple show and multiple seat
      -------
      m  :  1

Example:
Show     Seat      Status
 X        1        AVAILABLE
 X        2        BOOKED
 X        3        AVAILABLE
 X        4        BOOKED

 Y        6        BLOCKED
 Y        2        AVAILABLE
 Y        1        BOOKED

 Z        2        AVAILABLE

ShowSeat : Show
---------------
Say, X1 Show Seat have 1 show
Say, Y2 Show Seat have 1 show
Say, 2Z Show Seat gave 1 show
So, 1 ShowSeat have 1 Show(1 : 1)
----------
1 show(say, X) have multiple Show Seat (i.e) X1, X2,  X3, X4
1 show(say, Y) have multiple Show Seat (i.e) Y6, Y2, Y1
1 show(say, Z) have multiple Show Seat (i.e) Z2
So, 1 show have multiple showseat(m : 1)

ShowSeat : Seat
---------------
Say, X1 Show Seat have 1 seat
Say, Y2 Show Seat have 1 seat
Say, 2Z Show Seat gave 1 seat
So, 1 ShowSeat have 1 Seat(1 : 1)
----------
1 seat(say, 1) have multiple Show Seat (i.e) X1, Y1
1 seat(say, 2) have multiple Show Seat (i.e) X2, Y2, Z2
1 seat(say, 4) have multiple Show Seat (i.e) X4
So, 1 seat have multiple showseat(m : 1)
 */

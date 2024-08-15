package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    //already we have an id because of BaseModel(where it have common attributes)
    private String seatNumber;
    @ManyToOne
    private SeatType seatType;
    private int rowVal;
    private int colVal;
}
/*
Seat : SeatType
  1  :  1 = 1 particular seat belong only 1 seat type
  m  :  1 = 1 particular SeatType(say, PRIME seatType) allocated in mutiple seat
  -------
  m  :  1
 */

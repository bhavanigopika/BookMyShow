package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public  class SeatType extends BaseModel {
    private String name;
    /*
    SeatType means SILVER, GOLD, PLATINUM, LOUNGE, CLASSIC AND ALL...Each theatre have unique seat type...
    so don't use SeatType as enum...just mention it as name
     */

}

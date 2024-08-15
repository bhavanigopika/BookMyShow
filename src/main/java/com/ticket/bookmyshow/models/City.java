package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class City extends BaseModel {
    //id is common attributes which is already in BaseModel...so, no need to add it here
    private String name;
    //private List<Theatre> theatreList;
}

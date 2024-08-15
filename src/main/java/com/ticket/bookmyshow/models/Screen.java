package com.ticket.bookmyshow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Screen extends BaseModel{
    private String screenName;
    @OneToMany
    private List<Seat> seatList;
    //generally, cardinality not take between class and enum
    //Whenever we have list of enums, then use @ElementCollection(...)
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> featureList;
    private ScreenStatus screenStatus;
}

/*
Screen :  Seat
    1  :  m
    1  :  1 only one seat associated with one screen only physically
    -------
    1  :  m
 */

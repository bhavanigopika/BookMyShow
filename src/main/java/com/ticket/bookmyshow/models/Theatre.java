package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Theatre extends BaseModel{
    private String TheatreName;
    @OneToMany
    private List<Screen> screenList;
    @ManyToOne
    private City city;
}

/*
Theatre : City
   1    :  1
   m    :  1
   ---------
   m   :   1
 So, I have to add city_id in m side (i.e) So, store city in Theatre class
 So, Avoid List<Theatre> in City class for redundant data...

Theatre : Screen
1 : m
1 : 1
----------------
1 : m
So, I can add Theatre_id in Screen class during schema design to join the classes
Here, 1 theatre have List<Screen>...So add this one.
 */

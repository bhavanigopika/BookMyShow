package com.ticket.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel {
    private String movieName;
    @ManyToMany
    private List<Actor> actorList;
}
/*
movie : actor
   1  :  m
   m  :  1
   -------
   m : m  -> in schema diagram, I can create movie_actor table.
   1 movie have list<Actor> so, add this one.
 */
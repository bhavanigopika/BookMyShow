package com.ticket.bookmyshow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "shows")
@Getter
@Setter
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Screen screen;
    //screen have sound feature, display feature...
    // dolby_atmos is one of the feature in sound perspective and 2d, 3d, 4d are one of the features in display perspective
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> featureList;
}
/*
Show  :  Movie
  1   :  1 = current only one show have 1 movie only
  m   :  1 = 1 movie will happen in multiple shows
  --------
  m  : 1

Show  : Screen
   1  :  1 = current only one show played in 1 screen only
   m  :  1 = 1 screen(hall) have multiple show(in morning, in evening) will happen
   -------
   m  :  1
 */
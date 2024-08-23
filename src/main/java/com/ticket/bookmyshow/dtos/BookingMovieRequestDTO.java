package com.ticket.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingMovieRequestDTO {
    //expecting the user information about who is booking and all
    private Long userId;
    //for which show you are make the booking
    private Long showId;
    //which show and which seat to book
    private List<Long> showSeatIdList;
}

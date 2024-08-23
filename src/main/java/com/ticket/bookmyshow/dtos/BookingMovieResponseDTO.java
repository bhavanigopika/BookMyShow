package com.ticket.bookmyshow.dtos;

import com.ticket.bookmyshow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingMovieResponseDTO {
    private Booking booking;
    private ResponseStatus responseStatus;
}

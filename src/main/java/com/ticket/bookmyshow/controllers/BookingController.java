package com.ticket.bookmyshow.controllers;

import com.ticket.bookmyshow.dtos.BookingMovieRequestDTO;
import com.ticket.bookmyshow.dtos.BookingMovieResponseDTO;
import com.ticket.bookmyshow.dtos.ResponseStatus;
import com.ticket.bookmyshow.models.Booking;
import com.ticket.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    //Apply constructor dependency injection
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //accept the parameter from bookingMoveRequestDto which you get from client
    public BookingMovieResponseDTO bookMovie(BookingMovieRequestDTO bookingMovieRequestDTO){
        BookingMovieResponseDTO bookingMovieResponseDTO = new BookingMovieResponseDTO();
        //if something fails in response, it might show error...to avoid this, put it in try catch
        try {
            Booking booking = bookingService.bookMovie(bookingMovieRequestDTO.getUserId(),
                    bookingMovieRequestDTO.getShowId(),
                    bookingMovieRequestDTO.getShowSeatIdList());
            //once you got the information from bookingService, bookingMoveResponseDto going to provide the booking(ticket) and responseSatus
            bookingMovieResponseDTO.setBooking(booking);
            bookingMovieResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return bookingMovieResponseDTO;
        }catch(Exception ex){
            bookingMovieResponseDTO.setResponseStatus(ResponseStatus.FAILED);
            return bookingMovieResponseDTO;
        }
    }
}

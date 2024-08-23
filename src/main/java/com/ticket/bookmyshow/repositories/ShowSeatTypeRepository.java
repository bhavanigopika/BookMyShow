package com.ticket.bookmyshow.repositories;

import com.ticket.bookmyshow.models.SeatType;
import com.ticket.bookmyshow.models.Show;
import com.ticket.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    //From, showSeatType class, we want show and seatType
    List<ShowSeatType> findAllByShow(Show show);

    //you need 1 showSeatType, os use optional not list
    Optional<ShowSeatType> findBySeatType(SeatType seatType);
}

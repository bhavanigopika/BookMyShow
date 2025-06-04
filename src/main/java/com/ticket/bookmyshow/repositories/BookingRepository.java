package com.ticket.bookmyshow.repositories;

import com.ticket.bookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Override
    <S extends Booking> S save(S entity);
}

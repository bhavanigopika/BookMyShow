package com.ticket.bookmyshow.repositories;

import com.ticket.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    public Optional<User> findById(Long userId);

    //Write our own JPA methods(custom)
    Optional<User> findByEmail(String email);
}

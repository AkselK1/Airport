package com.cgi.airport.repository;

import com.cgi.airport.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByAvailability(boolean availability);

    List<Seat> findByMoreRoom(boolean moreRoom);

    List<Seat> findByFlight_Id(long flightId);
}

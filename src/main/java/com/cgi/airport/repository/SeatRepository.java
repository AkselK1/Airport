package com.cgi.airport.repository;

import com.cgi.airport.model.Flight;
import com.cgi.airport.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findBySeatNumberAndFlightAndMoreRoomAndBusinessClassAndWindowSeat(
            String seatNumber, Flight flight, boolean moreRoom, boolean businessClass, boolean windowSeat
    );
    List<Seat> findByAvailability(boolean availability);

    List<Seat> findByMoreRoom(boolean moreRoom);

    List<Seat> findByFlight_Id(long flightId);

    List<Seat> findByBusinessClass(boolean businessClass);

    List<Seat> findByWindowSeat(boolean windowSeat);
}

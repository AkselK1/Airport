package com.cgi.airport.repository;

import com.cgi.airport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByLeavingDateAndDestinationLocAndDepartureLocAndPrice(
            LocalDate leavingDate, String destinationLoc, String departureLoc, double price);

    List<Flight> findByPriceLessThan(double price);

    List<Flight> findByLeavingDate(LocalDate leavingDate);

    List<Flight> findByDestinationLoc(String destination);

    List<Flight> findByDepartureLoc(String departure);

}

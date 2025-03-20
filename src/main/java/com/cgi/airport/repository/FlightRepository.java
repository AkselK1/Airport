package com.cgi.airport.repository;

import com.cgi.airport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByLeavingDateAndReturningDateAndDestinationAndDepartureAndPrice(
            LocalDate leavingDate, LocalDate returningDate, String destination, String departure, double price);
    //Optional<Flight> findByReturningDate(LocalDate date);
    //Optional<Flight> findByDestination(String destination);
    //Optional<Flight> findByDeparture(String departure);
    //Optional<Flight> findByPrice(double price);
}

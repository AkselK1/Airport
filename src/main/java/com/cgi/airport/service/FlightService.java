package com.cgi.airport.service;

import com.cgi.airport.exception.FlightAlreadyExistsException;
import com.cgi.airport.model.Flight;
import com.cgi.airport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Returns all flight from the database
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight saveFlight(Flight inputFlight) throws FlightAlreadyExistsException {
        if (flightRepository.findByLeavingDateAndDestinationLocAndDepartureLocAndPrice(
                inputFlight.getLeavingDate(),
                inputFlight.getDestinationLoc(),
                inputFlight.getDepartureLoc(),
                inputFlight.getPrice()).isPresent()) {
            throw new FlightAlreadyExistsException("Flight with given details already exists!");
        }

        return flightRepository.save(inputFlight);
    }

    public List<Flight> filterByLeavingDate(LocalDate date) {
        return flightRepository.findByLeavingDate(date);
    }

    public List<Flight> filterByPrice(double price) {
        return flightRepository.findByPriceLessThan(price);
    }

    public List<Flight> filterByDestinationLoc(String destinationLoc) {
        return flightRepository.findByDestinationLoc(destinationLoc);
    }

    public List<Flight> filterByDepartureLoc(String departureLoc) {
        return flightRepository.findByDepartureLoc(departureLoc);
    }
}

package com.cgi.airport.service;

import com.cgi.airport.exception.FlightAlreadyExistsException;
import com.cgi.airport.model.Flight;
import com.cgi.airport.model.FlightBody;
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

    /**
    public Flight saveFlight(FlightBody flightBody) {
        Flight flight = new Flight();
        flight.setLeavingDate(flightBody.getLeavingDate());
        flight.setReturningDate(flightBody.getReturningDate());
        flight.setDestination(flightBody.getDestination());
        flight.setDeparture(flightBody.getDeparture());
        flight.setPrice(flightBody.getPrice());

        flight = flightRepository.save(flight);


        return flight;
    }
     */

    public Flight saveFlight(Flight inputFlight) throws FlightAlreadyExistsException {
        if (flightRepository.findByLeavingDateAndReturningDateAndDestinationAndDepartureAndPrice(
                inputFlight.getLeavingDate(),
                inputFlight.getReturningDate(),
                inputFlight.getDestination(),
                inputFlight.getDeparture(),
                inputFlight.getPrice()).isPresent()) {
            throw new FlightAlreadyExistsException("Flight with given details already exists!");
        }
/**
        Flight flight = new Flight();
        flight.setLeavingDate(inputFlight.getLeavingDate());
        flight.setReturningDate(inputFlight.getReturningDate());
        flight.setDestination(inputFlight.getDestination());
        flight.setDeparture(inputFlight.getDeparture());
        flight.setPrice(inputFlight.getPrice());
*/
        Flight savedFlight = flightRepository.save(inputFlight);

        return savedFlight;
    }
}

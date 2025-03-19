package com.cgi.airport.service;

import com.cgi.airport.model.Flight;
import com.cgi.airport.model.FlightBody;
import com.cgi.airport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

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
}

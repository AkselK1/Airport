package com.cgi.airport.controller;

import com.cgi.airport.model.Flight;
import com.cgi.airport.service.FlightService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights(
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String leavingDate,
            @RequestParam(required = false) Double price) {

        LocalDate parsedDate = null;
        if (leavingDate != null && !leavingDate.isEmpty()) {
            parsedDate = LocalDate.parse(leavingDate);
        }

        if (departure != null || destination != null || parsedDate != null || price != null) {
            return flightService.filterFlights(departure, destination, parsedDate, price);
        } else {
            return flightService.getAllFlights();
        }
    }
}

package com.cgi.airport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {
    @GetMapping("/flights")
    public String getFlights() {
        return "Available flights testing";
    }
}

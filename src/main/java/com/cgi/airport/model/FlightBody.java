package com.cgi.airport.model;

import java.time.LocalDate;

public class FlightBody {

    private LocalDate leavingDate;
    private LocalDate returningDate;
    private String destination;
    private String departure;
    private double price;

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public LocalDate getReturningDate() {
        return returningDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getDeparture() {
        return departure;
    }

    public double getPrice() {
        return price;
    }
}

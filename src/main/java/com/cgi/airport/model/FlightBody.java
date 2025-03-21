package com.cgi.airport.model;

import java.time.LocalDate;

public class FlightBody {

    private LocalDate leavingDate;
    private String destinationLoc;
    private String departureLoc;
    private double price;

    public LocalDate getLeavingDate() {
        return leavingDate;
    }


    public String getDestinationLoc() {
        return destinationLoc;
    }

    public String getDepartureLoc() {
        return departureLoc;
    }

    public double getPrice() {
        return price;
    }
}

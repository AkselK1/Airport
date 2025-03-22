package com.cgi.airport.service;

import com.cgi.airport.model.Flight;
import com.cgi.airport.model.Seat;
import com.cgi.airport.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    // Randomly generates seats that are taken.
    public void generateRandomTakenSeats(Flight flight) {

    }

    // Returns a list of seats, that have more leg room
    public List<Seat> filterByMoreRoom(boolean moreRoom) {
        return seatRepository.findByMoreRoom(moreRoom);
    }

    // Returns a list of seats, that are available (not taken)
    public List<Seat> filterByAvailability(boolean availability) {
        return seatRepository.findByAvailability(availability);
    }

    // Returns a list of seats, that belong to the flight which id is used for input
    public List<Seat> filterByFlightId(long flightId) {
        return seatRepository.findByFlight_Id(flightId);
    }
}

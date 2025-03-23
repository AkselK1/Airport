package com.cgi.airport.service;

import com.cgi.airport.exception.SeatAlreadyExistsException;
import com.cgi.airport.model.Flight;
import com.cgi.airport.model.Seat;
import com.cgi.airport.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    // Randomly generates seats that are taken.
    public HashMap<Integer, List<Character>> generateRandomTakenSeats() {
        // Keys are rows, values are columns
        HashMap<Integer, List<Character>> takenSeats = new HashMap<>();
        Random random = new Random();

        char[] businessCols = {'A', 'B', 'C', 'D'};
        char[] economyCols = {'A', 'B', 'C', 'D', 'E', 'F'};

        for (int i = 0; i < 62; i++) {
            // picks a random row from 1-30
            int row = random.nextInt(30) + 1;

            // checks how many columns that row has
            // business class has 4 columns, economy has 6
            char[] cols = (row <= 6) ? businessCols : economyCols;
            int colIndex = random.nextInt(cols.length);
            char colLabel = (cols[colIndex]);

            takenSeats.computeIfAbsent(row, r -> new ArrayList<>()).add(colLabel);
        }
        return takenSeats;
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

    public List<Seat> filterByBusinessClass(boolean businessClass) {
        return seatRepository.findByBusinessClass(businessClass);
    }

    public List<Seat> filterByWindowSeat(boolean windowSeat) {
        return seatRepository.findByWindowSeat(windowSeat);
    }

    public Seat saveSeat(Seat seat) throws SeatAlreadyExistsException {
        if (seatRepository.findBySeatNumberAndFlightAndMoreRoomAndBusinessClassAndWindowSeat(
                seat.getSeatNumber(),
                seat.getFlight(),
                seat.isMoreRoom(),
                seat.isBusinessClass(),
                seat.isWindowSeat()).isPresent()) {
            throw new SeatAlreadyExistsException("Seat with given details already exists!");
        }

        return seatRepository.save(seat);
    }

    public List<Seat> generateSeatsForFlight(Flight flight, HashMap<Integer, List<Character>> takenSeats) {
        List<Seat> seats = new ArrayList<>();

            for (int row = 1; row <= 30; row++) {
                char startingCol = 'A';
                char endingCol = (row <= 6) ? 'D' : 'F';

                for (char col = startingCol; col <= endingCol; col++) {
                    Seat seat = new Seat();
                    seat.setFlight(flight);
                    seat.setSeatNumber(row + "" + col);
                    seat.setBusinessClass(row <= 6);
                    seat.setWindowSeat(isWindowSeat(row, col));
                    seat.setMoreRoom(hasMoreLegRoom(row));

                    if (takenSeats.containsKey(row)) {
                        List<Character> values = takenSeats.get(row);
                        seat.setAvailability(!values.contains(col));
                    } else {
                        seat.setAvailability(true);
                    }
                    seats.add(seat);
                }
            }
        return seats;
    }

    // Checks if a certain seat is a window seat or not and returns: true = window seat, false = not a window seat
    private boolean isWindowSeat(int row, char col) {
        if (row <= 6) {
            return col == 'A' || col == 'D';
        } else {
            return col == 'A' || col == 'F';
        }
    }

    // Checks if a seat has extra leg room and returns: true = has extra leg room, false = doesn't have extra room.
    private boolean hasMoreLegRoom(int row) {
        return row == 7 || row == 8 || row == 9 || row == 28 || row == 29 || row == 30;
    }

}

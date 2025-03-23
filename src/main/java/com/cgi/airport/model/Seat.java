package com.cgi.airport.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    // true = available, false = not available/taken
    @Column(name = "availability", nullable = false)
    private boolean availability = true;

    // true = more leg room, false = no extra leg room
    @Column(name = "more_room", nullable = false)
    private boolean moreRoom = false;

    // true = is business class, false = not business class
    @Column(name = "business_class", nullable = false)
    private boolean businessClass = false;

    // true == is a window seat, false == is not a window seat
    @Column(name = "window_seat", nullable = false)
    private boolean windowSeat = false;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference
    private Flight flight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isMoreRoom() {
        return moreRoom;
    }

    public void setMoreRoom(boolean moreRoom) {
        this.moreRoom = moreRoom;
    }

    public boolean isBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(boolean businessClass) {
        this.businessClass = businessClass;
    }

    public boolean isWindowSeat() {
        return windowSeat;
    }

    public void setWindowSeat(boolean windowSeat) {
        this.windowSeat = windowSeat;
    }
}

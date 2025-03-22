package com.cgi.airport.model;


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

    // true = more leg room, false = less leg room
    @Column(name = "more_room", nullable = false)
    private boolean moreRoom = false;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
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
}

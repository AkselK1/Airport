package com.cgi.airport.exception;

public class FlightAlreadyExistsException extends Exception {
    public FlightAlreadyExistsException() {
        super();
    }

    public FlightAlreadyExistsException(String message) {
        super(message);
    }

    public FlightAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlightAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}

package com.cgi.airport.exception;

public class SeatAlreadyExistsException extends Exception {
    public SeatAlreadyExistsException() {
        super();
    }
    public SeatAlreadyExistsException(String message) {
        super(message);
    }
    public SeatAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public SeatAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}

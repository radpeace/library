package com.radpeace.library.exception;

public class AlreadyBookingException extends RuntimeException {
    public AlreadyBookingException(String message) {
        super(message);
    }
}

package com.radpeace.library.exception;

public class AlreadyIssuedException extends RuntimeException {
    public AlreadyIssuedException(String message) {
        super(message);
    }
}

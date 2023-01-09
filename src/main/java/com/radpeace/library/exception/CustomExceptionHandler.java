package com.radpeace.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseError noContentException(NoContentException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.OK.value(), HttpStatus.OK, exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError notFoundException(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError alreadyExistException(AlreadyExistException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(AlreadyIssuedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError alreadyIssuedException(AlreadyIssuedException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ResultIsEmptyException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseError resultIsEmptyException(ResultIsEmptyException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.OK.value(), HttpStatus.OK, exception.getMessage());
    }

    @ExceptionHandler(AlreadyBookingException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError alreadyBookingException(AlreadyBookingException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, exception.getMessage());
    }

}
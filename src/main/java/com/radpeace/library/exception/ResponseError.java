package com.radpeace.library.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ResponseError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();
    private final int status;
    private final HttpStatus error;
    private final String message;

    public ResponseError(int status, HttpStatus error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}

package com.github.valentina810.beauty_of_code_2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({HttpServerErrorException.class, UnsupportedOperationException.class})
    private ResponseEntity<ApiError> handelHttpServerErrorException(UnsupportedOperationException e) {
        return getResponseError(e.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<ApiError> handleNotFoundException(final NotFoundException e) {
        log.info(e.getMessage(), e);
        return getResponseError(e.getMessage(), NOT_FOUND);
    }

    private ResponseEntity<ApiError> getResponseError(String exceptionMessage, HttpStatus status) {
        log.error(exceptionMessage);
        return ResponseEntity.status(status).body(ApiError.builder()
                .status(status)
                .message(exceptionMessage)
                .date(LocalDateTime.now()).build());
    }
}

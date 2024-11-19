package com.github.valentina810.beauty_of_code_2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({HttpServerErrorException.class, UnsupportedOperationException.class})
    private ResponseEntity<ApiError> handelHttpServerErrorException(UnsupportedOperationException e) {
        return getResponseError(e.getMessage());
    }

    private ResponseEntity<ApiError> getResponseError(String exceptionMessage) {
        log.error(exceptionMessage);
        ApiError apiError = ApiError.builder()
                .status(INTERNAL_SERVER_ERROR)
                .message(exceptionMessage)
                .date(LocalDateTime.now()).build();
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}

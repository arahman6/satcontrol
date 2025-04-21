package edu.miu.cs.cs489.satcontrol.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SatelliteNotFoundException.class)
    public ResponseEntity<ApiError> handleSatelliteNotFound(SatelliteNotFoundException e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArg(IllegalArgumentException e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), Instant.now());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ApiError> handleUnsupported(UnsupportedOperationException e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), request.getRequestURI(), HttpStatus.CONFLICT.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAnyOther(Exception e, HttpServletRequest request) {
        ApiError error = new ApiError(e.getMessage(), request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Instant.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

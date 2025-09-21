package com.my.parkinglot.common.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.server.ResponseStatusException;

import com.my.parkinglot.common.api.error.ApiError;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ApiError> handleResponseStatus(ResponseStatusException ex, WebRequest request) {
		HttpStatus status = (HttpStatus) ex.getStatusCode();
		ApiError error = new ApiError(OffsetDateTime.now().toString(), status.value(), status.getReasonPhrase(),
				ex.getReason(), request.getDescription(false).replace("uri=", ""));
		return new ResponseEntity<>(error, status);
	}

	// Optional: handle other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex, WebRequest request) {
		ApiError error = new ApiError(OffsetDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error", ex.getMessage(), request.getDescription(false).replace("uri=", ""));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

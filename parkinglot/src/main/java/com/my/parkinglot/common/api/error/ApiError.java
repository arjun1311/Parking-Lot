package com.my.parkinglot.common.api.error;

public record ApiError(
	    String timestamp,
	    int status,
	    String error,
	    String message,
	    String path
	) {}

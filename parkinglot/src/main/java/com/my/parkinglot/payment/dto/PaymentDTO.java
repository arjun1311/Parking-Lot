package com.my.parkinglot.payment.dto;

import com.my.parkinglot.common.enums.PaymentMethod;

public record PaymentDTO(
	    Long ticketId,
	    Double amount,
	    PaymentMethod method
	) {}


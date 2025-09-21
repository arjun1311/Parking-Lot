package com.my.parkinglot.ticket.dto;

import java.time.LocalDateTime;

public record TicketDTO(
	    Long slotId,
	    Long vehicleId,
	    LocalDateTime entryTime,
	    Integer bookedHours
	) {}


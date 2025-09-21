package com.my.parkinglot.notification.dto;

import com.my.parkinglot.common.enums.NotificationType;

public record NotificationDTO(
	    Long ticketId,
	    NotificationType type
	) {}


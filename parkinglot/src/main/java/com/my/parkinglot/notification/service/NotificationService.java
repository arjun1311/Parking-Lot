package com.my.parkinglot.notification.service;

import com.my.parkinglot.notification.dto.NotificationDTO;
import com.my.parkinglot.notification.entity.Notification;

public interface NotificationService {

	Notification createCustomNotification(NotificationDTO dto);

	Notification getTicketNotifications(Long ticketId);

	Notification listAllNotificationForUser(Long userId);

}

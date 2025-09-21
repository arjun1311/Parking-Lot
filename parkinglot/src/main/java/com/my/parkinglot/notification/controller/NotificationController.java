package com.my.parkinglot.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.parkinglot.notification.service.NotificationService;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.my.parkinglot.notification.dto.NotificationDTO;
import com.my.parkinglot.notification.entity.Notification;
	

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Parking Lot Notification", description = "Parking Lot Notification management APIs")
public class NotificationController {
	
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDTO dto) {
    	Notification created = notificationService.createCustomNotification(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Notification> getTicketNotifications(@PathVariable Long ticketId) {
        return ResponseEntity.ok(notificationService.getTicketNotifications(ticketId));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Notification> listAllNotificationForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.listAllNotificationForUser(userId));
    }


}

package com.my.parkinglot.notification.repository;

import com.my.parkinglot.notification.entity.Notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByTicketId(Long ticketId);
}

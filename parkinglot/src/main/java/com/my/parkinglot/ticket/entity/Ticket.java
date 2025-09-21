package com.my.parkinglot.ticket.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.my.parkinglot.common.enums.TicketStatus;
import com.my.parkinglot.notification.entity.Notification;
import com.my.parkinglot.payment.entity.Payment;
import com.my.parkinglot.slot.entity.Slot;
import com.my.parkinglot.ticket.entity.Ticket;
import com.my.parkinglot.vehicle.entity.Vehicle;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Integer bookedHours;
    private Integer overstayMinutes;

    @Enumerated(EnumType.STRING)
    private TicketStatus status; // ACTIVE, CLOSED, LOST

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "ticket")
    private List<Notification> notifications = new ArrayList<>();
}


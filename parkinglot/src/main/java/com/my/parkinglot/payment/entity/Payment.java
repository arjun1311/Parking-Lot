package com.my.parkinglot.payment.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.my.parkinglot.common.enums.PaymentMethod;
import com.my.parkinglot.common.enums.PaymentStatus;
import com.my.parkinglot.ticket.entity.Ticket;

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
@Table(name = "payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method; // CASH, UPI, CARD, WALLET

    private LocalDateTime paidAt;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}


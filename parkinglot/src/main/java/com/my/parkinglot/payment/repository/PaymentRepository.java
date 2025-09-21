package com.my.parkinglot.payment.repository;

import com.my.parkinglot.payment.entity.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTicketId(Long ticketId);
}


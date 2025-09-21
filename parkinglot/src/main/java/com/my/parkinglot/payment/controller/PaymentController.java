package com.my.parkinglot.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.parkinglot.payment.service.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.my.parkinglot.payment.dto.PaymentDTO;
import com.my.parkinglot.payment.entity.Payment;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Parking Lot Payment", description = "Parking Lot Payment management APIs")
public class PaymentController {
	
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/ticket/{ticketId}")
    public ResponseEntity<Payment> initiatePayment(@RequestBody PaymentDTO dto) {
    	Payment created = paymentService.initiatePayment(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentDetails(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentDetails(paymentId));
    }
    
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Payment> getTicketPaymentStatus(@PathVariable Long ticketId) {
        return ResponseEntity.ok(paymentService.getTicketPaymentStatus(ticketId));
    }

}

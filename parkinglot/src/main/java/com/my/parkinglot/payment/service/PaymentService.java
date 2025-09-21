package com.my.parkinglot.payment.service;

import com.my.parkinglot.payment.dto.PaymentDTO;
import com.my.parkinglot.payment.entity.Payment;

public interface PaymentService {

	Payment initiatePayment(PaymentDTO dto);

	Payment getPaymentDetails(Long paymentId);

	Payment getTicketPaymentStatus(Long ticketId);

}

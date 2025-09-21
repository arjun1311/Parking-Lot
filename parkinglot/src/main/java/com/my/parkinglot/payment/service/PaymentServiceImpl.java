package com.my.parkinglot.payment.service;

import org.springframework.stereotype.Service;

import com.my.parkinglot.payment.dto.PaymentDTO;
import com.my.parkinglot.payment.entity.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Payment initiatePayment(PaymentDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment getPaymentDetails(Long paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment getTicketPaymentStatus(Long ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

}

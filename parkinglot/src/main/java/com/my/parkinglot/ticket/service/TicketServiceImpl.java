package com.my.parkinglot.ticket.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.my.parkinglot.common.enums.PaymentStatus;
import com.my.parkinglot.common.enums.TicketStatus;
import com.my.parkinglot.level.repository.LevelRepository;
import com.my.parkinglot.payment.entity.Payment;
import com.my.parkinglot.slot.entity.Slot;
import com.my.parkinglot.slot.repository.SlotRepository;
import com.my.parkinglot.ticket.dto.TicketDTO;
import com.my.parkinglot.ticket.entity.Ticket;
import com.my.parkinglot.ticket.repository.TicketRepository;
import com.my.parkinglot.vehicle.entity.Vehicle;
import com.my.parkinglot.vehicle.repository.VehicleRepository;

@Service
public class TicketServiceImpl implements TicketService {
	
	private final TicketRepository ticketRepository;
	private final SlotRepository slotRepository;
	private final VehicleRepository vehicleRepository;

	public TicketServiceImpl(TicketRepository ticketRepository,SlotRepository slotRepository,VehicleRepository vehicleRepository) {
		this.ticketRepository = ticketRepository;
		this.slotRepository = slotRepository;
		this.vehicleRepository = vehicleRepository;
	}

	@Transactional
	@Override
	public Ticket createTicket(TicketDTO dto) {
		Vehicle vehicle = vehicleRepository.findById(dto.vehicleId()).orElseThrow(
				() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Vehicle not found with id: " + dto.vehicleId()));
		Slot slot = slotRepository.findById(dto.slotId()).orElseThrow(
				() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Slot not found with id: " + dto.slotId()));
		if (slot.isOccupied()) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slot is already occupied");
		}
		slot.setOccupied(true);
		slotRepository.save(slot);
		
		Ticket ticket = new Ticket();
		ticket.setVehicle(vehicle);
		ticket.setSlot(slot);
		ticket.setEntryTime(dto.entryTime());
		ticket.setBookedHours(dto.bookedHours());
		ticket.setExitTime(null);
		ticket.setOverstayMinutes(0);
		ticket.setStatus(TicketStatus.ACTIVE);
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket getTicketDetails(Long ticketId) {
		return ticketRepository.findById(ticketId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found with id: " + ticketId));
	}

	@Override
	@Transactional
	public Ticket closeTicket(Long ticketId) {
	    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
	            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found with id: " + ticketId));

	    if (ticket.getStatus() != TicketStatus.ACTIVE) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only ACTIVE tickets can be closed");
	    }

	    LocalDateTime exitTime = LocalDateTime.now();
	    ticket.setExitTime(exitTime);

	    long minutesStayed = java.time.Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
	    int bookedMinutes = ticket.getBookedHours() * 60;
	    int overstayMinutes = (int) Math.max(0, minutesStayed - bookedMinutes);
	    ticket.setOverstayMinutes(overstayMinutes);

	    double hourlyRate = 20.00;
	    double totalAmount = ticket.getBookedHours() * hourlyRate;
	    if (overstayMinutes > 0) {
	        totalAmount += (overstayMinutes / 60.0) * hourlyRate * 1.5;
	    }

	    Payment payment = ticket.getPayment();
	    if (payment == null) {
	        payment = new Payment();
	        payment.setTicket(ticket);
	    }
	    payment.setAmount(totalAmount);
	    payment.setStatus(PaymentStatus.PENDING); // initially pending
	    ticket.setPayment(payment);
	    payment.setPaidAt(null); // not paid yet

	    ticket.setStatus(TicketStatus.COMPLETED);

	    // Free the slot
	    Slot slot = ticket.getSlot();
	    slot.setOccupied(false);
	    slotRepository.save(slot);

	    return ticketRepository.save(ticket);
	}


	@Override
	public Ticket getActiveTicketByVehicle(Long vehicleId) {
		return ticketRepository.findByVehicle_IdAndStatus(vehicleId, TicketStatus.ACTIVE)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Active ticket not found for vehicle id: " + vehicleId));
	}

}

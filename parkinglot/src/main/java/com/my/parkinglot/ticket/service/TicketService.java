package com.my.parkinglot.ticket.service;

import com.my.parkinglot.ticket.dto.TicketDTO;
import com.my.parkinglot.ticket.entity.Ticket;

public interface TicketService {

	Ticket createTicket(TicketDTO dto);

	Ticket getTicketDetails(Long ticketId);

	Ticket closeTicket(Long ticketId);

	Ticket getActiveTicketByVehicle(Long vehicleNumber);

}

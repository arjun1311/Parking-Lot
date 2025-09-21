package com.my.parkinglot.ticket.repository;

import com.my.parkinglot.common.enums.TicketStatus;
import com.my.parkinglot.ticket.entity.Ticket;
import com.my.parkinglot.vehicle.entity.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(TicketStatus status);
	Optional<Ticket> findByVehicle_IdAndStatus(Long vehicleNumber, TicketStatus active);
}

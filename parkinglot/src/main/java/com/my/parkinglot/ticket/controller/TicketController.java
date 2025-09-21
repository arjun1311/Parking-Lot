package com.my.parkinglot.ticket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.parkinglot.ticket.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.my.parkinglot.slot.entity.Slot;
import com.my.parkinglot.ticket.dto.TicketDTO;
import com.my.parkinglot.ticket.entity.Ticket;

@RestController
@RequestMapping("/api/v1/tickets")
@Tag(name = "Parking Lot Ticket", description = "Parking Lot Ticket management APIs")
public class TicketController {
	
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDTO dto) {
    	Ticket created = ticketService.createTicket(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{ticketId}")
	@Operation(summary = "Get Ticket By Id")
	@ApiResponse(responseCode = "200", description = "Get Ticket By Id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)))
    public ResponseEntity<Ticket> getTicketDetails(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.getTicketDetails(ticketId));
    }
    
    @PatchMapping("/{ticketId}/close")
    public ResponseEntity<Ticket> closeTicket(
            @PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.closeTicket(ticketId));
    }
    
    @GetMapping("/vehicle/{vehicleNumber}")
	@Operation(summary = "Get Ticket By Vehicle Number")
	@ApiResponse(responseCode = "200", description = "Get Ticket By Vehicle Number", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)))
    public ResponseEntity<Ticket> getActiveTicketByVehicle(@PathVariable Long vehicleNumber) {
        return ResponseEntity.ok(ticketService.getActiveTicketByVehicle(vehicleNumber));
    }

}

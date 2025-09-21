package com.my.parkinglot.parkinglot.controller;

import com.my.parkinglot.parkinglot.dto.ParkingLotDTO;
import com.my.parkinglot.parkinglot.entity.ParkingLot;
import com.my.parkinglot.parkinglot.service.ParkingLotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-lots")
@Tag(name = "Parking Lot", description = "Parking Lot management APIs")
public class ParkingLotController {

	private final ParkingLotService parkingLotService;

	public ParkingLotController(ParkingLotService parkingLotService) {
		this.parkingLotService = parkingLotService;
	}

	@PostMapping
	public ResponseEntity<ParkingLot> createParkingLot(@RequestBody ParkingLotDTO dto) {
		ParkingLot created = parkingLotService.createParkingLot(dto);
		return ResponseEntity.status(201).body(created);
	}

	@GetMapping
	@Operation(summary = "Get all parking lots")
	@ApiResponse(responseCode = "200", description = "List of all parking lots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingLot.class)))
	public ResponseEntity<List<ParkingLot>> getAllParkingLots() {
		return ResponseEntity.ok(parkingLotService.getAllParkingLots());
	}

	@GetMapping("/{lotId}")
	@Operation(summary = "Get Specific parking lots")
	@ApiResponse(responseCode = "200", description = "Found parking lot", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingLot.class)))
	public ResponseEntity<ParkingLot> getParkingLot(@PathVariable Long lotId) {
		return ResponseEntity.ok(parkingLotService.getParkingLot(lotId));
	}

	@PatchMapping("/{lotId}")
	public ResponseEntity<ParkingLot> updateParkingLot(@PathVariable Long lotId, @RequestBody ParkingLotDTO dto) {
		return ResponseEntity.ok(parkingLotService.updateParkingLot(lotId, dto));
	}

	@DeleteMapping("/{lotId}")
	public ResponseEntity<Void> deleteParkingLot(@PathVariable Long lotId) {
		parkingLotService.deleteParkingLot(lotId);
		return ResponseEntity.noContent().build();
	}
}

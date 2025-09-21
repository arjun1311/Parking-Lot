package com.my.parkinglot.vehicle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.parkinglot.vehicle.service.VehicleService;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.my.parkinglot.vehicle.dto.VehicleDTO;
import com.my.parkinglot.vehicle.entity.Vehicle;
		
@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Parking Lot Vehicle", description = "Parking Lot Vehicle management APIs")
public class VehicleController {
	
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody VehicleDTO dto) {
    	Vehicle created = vehicleService.registerVehicle(dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{vehicleNumber}")
    public ResponseEntity<Vehicle> getVehicleDetailsByNumber(@PathVariable Long vehicleNumber) {
        return ResponseEntity.ok(vehicleService.getVehicleDetailsByNumber(vehicleNumber));
    }
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<Vehicle> getAllVehiclesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(vehicleService.getAllVehiclesByOwner(ownerId));
    }

}

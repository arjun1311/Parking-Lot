package com.my.parkinglot.vehicle.dto;

import com.my.parkinglot.common.enums.VehicleType;

public record VehicleDTO(
	    String licensePlate,
	    VehicleType vehicleType
	) {}


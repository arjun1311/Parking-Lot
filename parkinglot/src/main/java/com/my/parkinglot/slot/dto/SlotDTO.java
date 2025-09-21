package com.my.parkinglot.slot.dto;

import com.my.parkinglot.common.enums.VehicleType;

public record SlotDTO(
	    String slotNumber,
	    VehicleType vehicleType
	) {}


package com.my.parkinglot.vehicle.service;

import com.my.parkinglot.vehicle.dto.VehicleDTO;
import com.my.parkinglot.vehicle.entity.Vehicle;

public interface VehicleService {

	Vehicle registerVehicle(VehicleDTO dto);

	Vehicle getVehicleDetailsByNumber(Long vehicleNumber);

	Vehicle getAllVehiclesByOwner(Long ownerId);

}

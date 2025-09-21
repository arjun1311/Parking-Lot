package com.my.parkinglot.parkinglot.service;

import java.util.List;

import com.my.parkinglot.parkinglot.dto.ParkingLotDTO;
import com.my.parkinglot.parkinglot.entity.ParkingLot;

public interface ParkingLotService {

	ParkingLot createParkingLot(ParkingLotDTO dto);

	List<ParkingLot> getAllParkingLots();

	ParkingLot getParkingLot(Long lotId);

	ParkingLot updateParkingLot(Long lotId, ParkingLotDTO dto);

	void deleteParkingLot(Long lotId);

}

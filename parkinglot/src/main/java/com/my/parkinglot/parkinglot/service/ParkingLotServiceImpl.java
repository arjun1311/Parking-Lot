package com.my.parkinglot.parkinglot.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.my.parkinglot.parkinglot.dto.ParkingLotDTO;
import com.my.parkinglot.parkinglot.entity.ParkingLot;
import com.my.parkinglot.parkinglot.repository.ParkingLotRepository;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
	
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

	@Override
	public ParkingLot createParkingLot(ParkingLotDTO dto) {
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setName(dto.name());
		parkingLot.setLocation(dto.location());
		parkingLot.setDescription(dto.description());
		
		parkingLot = parkingLotRepository.save(parkingLot);
		return parkingLot;
	}

	@Override
	public List<ParkingLot> getAllParkingLots() {
		return parkingLotRepository.findAll();
	}

	@Override
	public ParkingLot getParkingLot(Long lotId) {
	    return parkingLotRepository.findById(lotId)
	            .orElseThrow(() ->
	                new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "Parking lot not found with id: " + lotId
	                )
	            );
	}

	@Override
	public ParkingLot updateParkingLot(Long lotId, ParkingLotDTO dto) {
		ParkingLot parkingLot = parkingLotRepository.findById(lotId)
				.orElseThrow(() -> new RuntimeException("Parking lot not found with id: " + lotId));
		parkingLot.setName(dto.name());
		parkingLot.setLocation(dto.location());
		parkingLot.setDescription(dto.description());
		parkingLot = parkingLotRepository.save(parkingLot);
		return parkingLot;
	}

	@Override
	public void deleteParkingLot(Long lotId) {
		parkingLotRepository.deleteById(lotId);	
	}

}

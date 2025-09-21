package com.my.parkinglot.level.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.my.parkinglot.level.dto.LevelDTO;
import com.my.parkinglot.level.entity.Level;
import com.my.parkinglot.level.repository.LevelRepository;
import com.my.parkinglot.parkinglot.entity.ParkingLot;
import com.my.parkinglot.parkinglot.repository.ParkingLotRepository;


@Service
public class LevelServiceImpl implements LevelService {
	
    private final LevelRepository levelRepository;
    private final ParkingLotRepository parkingLotRepository;

    public LevelServiceImpl(LevelRepository levelRepository, ParkingLotRepository parkingLotRepository) {
        this.levelRepository = levelRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

	@Override
	public Level addNewLevel(LevelDTO dto) {
		ParkingLot parkingLot = parkingLotRepository.findById(dto.parkingLotId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Parking lot not found with id: " + dto.parkingLotId()));
		Level level = new Level();
		level.setLevelNumber(dto.levelNumber());
		level.setParkingLot(parkingLot);
		level = levelRepository.save(level);
		return level;
	}

	@Override
	public List<Level> getAllLevels() {
		return levelRepository.findAll();
	}

	@Override
	public Level getSpecificLevel(Long levelId) {
		return levelRepository.findById(levelId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found with id: " + levelId));
	}

	@Override
	public Level updateLevelDetails(Long levelId, LevelDTO dto) {
		Level level = levelRepository.findById(levelId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found with id: " + levelId));
		ParkingLot parkingLot = parkingLotRepository.findById(dto.parkingLotId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Parking lot not found with id: " + dto.parkingLotId()));
		level.setLevelNumber(dto.levelNumber());
		level.setParkingLot(parkingLot);
		level = levelRepository.save(level);
		return level;
	}

	@Override
	public void deleteLevel(Long levelId) {
        levelRepository.deleteById(levelId);	
	}
}

package com.my.parkinglot.slot.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.my.parkinglot.level.entity.Level;
import com.my.parkinglot.level.repository.LevelRepository;
import com.my.parkinglot.slot.dto.SlotDTO;
import com.my.parkinglot.slot.entity.Slot;
import com.my.parkinglot.slot.repository.SlotRepository;

@Service
public class SlotServiceImpl implements SlotService {

	private final SlotRepository slotRepository;
	private final LevelRepository levelRepository;

	public SlotServiceImpl(SlotRepository slotRepository, LevelRepository levelRepository) {
		this.slotRepository = slotRepository;
		this.levelRepository = levelRepository;
	}
	
	private Boolean slotExistsByLevelAndNumber(Long levelId, String slotNumber) {
        return slotRepository.existsByLevelIdAndSlotNumber(levelId, slotNumber);
    }

	@Override
	public Slot addParkingSlot(Long levelId,SlotDTO dto) {
		if (slotExistsByLevelAndNumber(levelId, dto.slotNumber())) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slot number already exists for this level");
		}
		Level level = levelRepository.findById(levelId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found with id: " + levelId));
		Slot slot = new Slot();
		slot.setLevel(level);
		slot.setVehicleType(dto.vehicleType());
		slot.setSlotNumber(dto.slotNumber());
		slot.setOccupied(false);
		return slotRepository.save(slot);
	}

	@Override
	public Slot getSlotDetails(Long levelId, Long slotId) {
		return slotRepository.findById(slotId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found with id: " + slotId));
	}

	@Override
	public List<Slot> getAvailableSlots(Long levelId) {
	    List<Slot> slots = slotRepository.findByLevelIdAndIsOccupiedFalse(levelId);
	    if (slots.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No available slots found for level: " + levelId);
	    }
	    return slots;
	}


	@Override
	public List<Slot> getAllParkingSlots(Long levelId) {
		return slotRepository.findAll();
	}

	@Override
	public Slot updateSlot(Long levelId, Long slotId, SlotDTO dto) {
		if (slotExistsByLevelAndNumber(levelId, dto.slotNumber())) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slot number already exists for this level");
		}
		Slot slot = slotRepository.findById(slotId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found with id: " + slotId));
		Level level = levelRepository.findById(levelId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found with id: " + levelId));
		slot.setLevel(level);
		slot.setVehicleType(dto.vehicleType());
		slot.setSlotNumber(dto.slotNumber());
		return slotRepository.save(slot);
	}

	@Override
	public void removeSlot(Long levelId,Long slotId) {
		slotRepository.deleteById(slotId);

	}
	
	@Override
	public Slot occupySlot(Long levelId,Long slotId) {
	    Slot slot = getSlotDetails(levelId, slotId);
	    if (slot.isOccupied()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slot already occupied");
	    }
	    slot.setOccupied(true);
	    return slotRepository.save(slot);
	}

	@Override
	public Slot freeSlot(Long levelId,Long slotId) {
	    Slot slot = getSlotDetails(levelId,slotId);
	    if (!slot.isOccupied()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slot is already free");
	    }
	    slot.setOccupied(false);
	    return slotRepository.save(slot);
	}


}

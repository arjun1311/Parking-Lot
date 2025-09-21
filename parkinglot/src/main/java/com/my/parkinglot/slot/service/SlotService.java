package com.my.parkinglot.slot.service;

import java.util.List;

import com.my.parkinglot.slot.dto.SlotDTO;
import com.my.parkinglot.slot.entity.Slot;

public interface SlotService {

	Slot addParkingSlot(Long levelId, SlotDTO dto);

	Slot getSlotDetails(Long levelId, Long slotId);

	List<Slot> getAvailableSlots(Long levelId);

	List<Slot> getAllParkingSlots(Long levelId);

	Slot updateSlot(Long levelId, Long slotId, SlotDTO dto);

	void removeSlot(Long levelId, Long slotId);

	Slot occupySlot(Long levelId, Long slotId);

	Slot freeSlot(Long levelId, Long slotId);

}

package com.my.parkinglot.slot.repository;

import com.my.parkinglot.level.entity.Level;
import com.my.parkinglot.slot.entity.Slot;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByLevelIdAndIsOccupiedFalse(Long levelId);

	boolean existsByLevelIdAndSlotNumber(Long levelId, String slotNumber);

}


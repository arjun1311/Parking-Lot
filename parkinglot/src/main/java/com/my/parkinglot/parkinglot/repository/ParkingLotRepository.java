package com.my.parkinglot.parkinglot.repository;

import com.my.parkinglot.parkinglot.entity.ParkingLot;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    // Example: find ParkingLot by name
    Optional<ParkingLot> findByName(String name);
}

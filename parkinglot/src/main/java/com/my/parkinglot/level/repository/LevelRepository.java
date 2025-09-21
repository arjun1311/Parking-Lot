package com.my.parkinglot.level.repository;

import com.my.parkinglot.level.entity.Level;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    List<Level> findByParkingLotId(Long lotId);
}


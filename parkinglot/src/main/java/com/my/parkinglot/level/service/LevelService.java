package com.my.parkinglot.level.service;

import java.util.List;

import com.my.parkinglot.level.dto.LevelDTO;
import com.my.parkinglot.level.entity.Level;

public interface LevelService {

	Level addNewLevel(LevelDTO dto);

	List<Level> getAllLevels();

	Level getSpecificLevel(Long levelId);

	Level updateLevelDetails(Long levelId, LevelDTO dto);

	void deleteLevel(Long levelId);

}

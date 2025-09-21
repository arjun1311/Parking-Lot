package com.my.parkinglot.level.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.my.parkinglot.level.service.LevelService;
import com.my.parkinglot.parkinglot.entity.ParkingLot;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.my.parkinglot.level.dto.LevelDTO;
import com.my.parkinglot.level.entity.Level;

@RestController
@RequestMapping("/api/v1/parking-lots/levels")
@Tag(name = "Parking Lot Level", description = "Parking Lot Level management APIs")
public class LevelController {
	
    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity<Level> addNewLevel(@RequestBody LevelDTO dto) {
    	Level created = levelService.addNewLevel(dto);
        return ResponseEntity.status(201).body(created);
    }

	@GetMapping
	@Operation(summary = "Get all Levels")
	@ApiResponse(responseCode = "200", description = "List of all Levels", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Level.class)))
    public ResponseEntity<List<Level>> getAllLevels() {
        return ResponseEntity.ok(levelService.getAllLevels());
    }

    @GetMapping("/{levelId}")
	@Operation(summary = "Get Level by ID")
	@ApiResponse(responseCode = "200", description = "Get Specific Level", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Level.class)))
    public ResponseEntity<Level> getSpecificLevel(@PathVariable Long levelId) {
        return ResponseEntity.ok(levelService.getSpecificLevel(levelId));
    }

    @PatchMapping("/{levelId}")
    public ResponseEntity<Level> updateLevelDetails(
            @PathVariable Long levelId,
            @RequestBody LevelDTO dto) {
        return ResponseEntity.ok(levelService.updateLevelDetails(levelId, dto));
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long levelId) {
    	levelService.deleteLevel(levelId);
        return ResponseEntity.noContent().build();
    }

}

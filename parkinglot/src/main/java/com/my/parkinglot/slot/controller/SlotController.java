package com.my.parkinglot.slot.controller;

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

import com.my.parkinglot.slot.service.SlotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.my.parkinglot.level.entity.Level;
import com.my.parkinglot.slot.dto.SlotDTO;
import com.my.parkinglot.slot.entity.Slot;

@RestController
@RequestMapping("/api/v1/levels/{levelId}/slots")
@Tag(name = "Parking Lot Slot", description = "Parking Lot Slot management APIs")
public class SlotController {
	
    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Slot> addParkingSlot(@PathVariable Long levelId,@RequestBody SlotDTO dto) {
    	Slot created = slotService.addParkingSlot(levelId,dto);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
	@Operation(summary = "List all Slots")
	@ApiResponse(responseCode = "200", description = "List all Slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Slot.class)))
    public ResponseEntity<List<Slot>> getAllParkingSlots(@PathVariable Long levelId) {
        return ResponseEntity.ok(slotService.getAllParkingSlots(levelId));
    }

    @GetMapping("/available")
	@Operation(summary = "List available Slots")
	@ApiResponse(responseCode = "200", description = "List available Slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Slot.class)))
    public ResponseEntity<List<Slot>> getAvailableSlots(@PathVariable Long levelId) {
        return ResponseEntity.ok(slotService.getAvailableSlots(levelId));
    }

    @GetMapping("/{slotId}")
	@Operation(summary = "Find status of specific Slot")
	@ApiResponse(responseCode = "200", description = "Find status of specific Slot", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Slot.class)))
    public ResponseEntity<Slot> getSlotDetails(@PathVariable Long levelId, @PathVariable Long slotId) {
        return ResponseEntity.ok(slotService.getSlotDetails(levelId, slotId));
    }

    @PatchMapping("/{slotId}")
    public ResponseEntity<Slot> updateSlot(
    		@PathVariable Long levelId,
    		@PathVariable Long slotId,
            @RequestBody SlotDTO dto) {
        return ResponseEntity.ok(slotService.updateSlot(levelId, slotId, dto));
    }

    @DeleteMapping("/{slotId}")
    public ResponseEntity<Void> removeSlot(@PathVariable Long levelId, @PathVariable Long slotId) {
    	slotService.removeSlot(levelId, slotId);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{slotId}/occupy")
    public ResponseEntity<Slot> occupySlot(@PathVariable Long levelId, @PathVariable Long slotId) {
        return ResponseEntity.ok(slotService.occupySlot(levelId, slotId));
    }

    @PatchMapping("/{slotId}/free")
    public ResponseEntity<Slot> freeSlot(@PathVariable Long levelId, @PathVariable Long slotId) {
        return ResponseEntity.ok(slotService.freeSlot(levelId, slotId));
    }


}

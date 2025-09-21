package com.my.parkinglot.level.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.my.parkinglot.parkinglot.entity.ParkingLot;
import com.my.parkinglot.slot.entity.Slot;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "level")
@Data
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int levelNumber;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    @JsonBackReference
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Slot> slots = new ArrayList<>();
}


package com.my.parkinglot.parkinglot.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.my.parkinglot.level.entity.Level;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "parking_lot")
@Data
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private String description; // Optional

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Level> levels = new ArrayList<>();
}

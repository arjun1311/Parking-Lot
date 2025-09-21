package com.my.parkinglot.slot.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.my.parkinglot.common.enums.VehicleType;
import com.my.parkinglot.level.entity.Level;
import com.my.parkinglot.ticket.entity.Ticket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "slot")
@Data
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType; // TWO_WHEELER, FOUR_WHEELER

    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonBackReference
    private Level level;

    @OneToMany(mappedBy = "slot")
    private List<Ticket> tickets = new ArrayList<>();
}


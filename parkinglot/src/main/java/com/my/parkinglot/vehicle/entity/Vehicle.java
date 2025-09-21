package com.my.parkinglot.vehicle.entity;

import java.util.ArrayList;
import java.util.List;

import com.my.parkinglot.common.enums.VehicleType;
import com.my.parkinglot.ticket.entity.Ticket;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle")
    private List<Ticket> tickets = new ArrayList<>();
}


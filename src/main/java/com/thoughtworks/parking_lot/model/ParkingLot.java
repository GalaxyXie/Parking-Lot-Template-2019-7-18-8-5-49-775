package com.thoughtworks.parking_lot.model;


import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name="parkinglot")

public class ParkingLot {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private  String name;
    @Min(0)
    private int capacity = 10;
    private String position;
    public ParkingLot(){};

    public int getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(int parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    @Min(0)
    private int parkingPlace = 10;
    public ParkingLot(String name, int capacity, String position) {
        this.name = name;
        this.capacity = capacity;
        this.position = position;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}


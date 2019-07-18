package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.sevice.ParkingLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLostService parkingLostService;
    @PostMapping("/parking-lots")
    public ResponseEntity createParkingLost(@RequestBody ParkingLot parkingLot){
        return parkingLostService.createParkingLost(parkingLot);
    }
    @DeleteMapping("/parking-lots/{index}")
    public ResponseEntity DeleteCompany(@PathVariable int index){
        return parkingLostService.DeleteCompany(index);
    }
}

package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @PostMapping("/parking-lots")
    public ResponseEntity createParkingLost(@RequestBody ParkingLot parkingLot){
        parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(parkingLot);
    }
    @DeleteMapping("/parking-lots")
    public ResponseEntity deleteParkingLost(@RequestBody ParkingLot parkingLot){
        parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(parkingLot);
    }
}

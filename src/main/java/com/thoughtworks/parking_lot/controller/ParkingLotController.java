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
        return ResponseEntity.ok(parkingLostService.createParkingLost(parkingLot));
    }

    @DeleteMapping(value = "/parking-lots/{Id}")
    public ResponseEntity DeleteParkingLot(@PathVariable int Id){
        parkingLostService.DeleteParkingLot(Id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/parking-lots",params = {"page"})
    public ResponseEntity FindParkingLotsByPage(@RequestParam int page){
        return ResponseEntity.ok(parkingLostService.FindParkingLotsByPage(page));
    }
    @GetMapping("/parking-lots/{Id}")
    public ResponseEntity FindParkingLotById(@PathVariable int Id){
        return ResponseEntity.ok(parkingLostService.FindParkingLotById(Id));
    }

    @PutMapping("/parking-lots/{Id}")
    public ResponseEntity UpdateParkingLotsById(@PathVariable int Id, @RequestBody ParkingLot parkingLot){
        return ResponseEntity.ok(parkingLostService.UpdateParkingLotCapacityById(Id,parkingLot));
    }
}

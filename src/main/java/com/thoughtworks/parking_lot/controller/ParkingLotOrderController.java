package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.Car;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import com.thoughtworks.parking_lot.sevice.ParkingLotOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotOrderController {
    @Autowired
    private ParkingLotOrderService parkingLotOrderService;

    @PostMapping("/orders")
    public ResponseEntity generateOrder(@RequestBody Car car) {
        try {
            ParkingLotOrder parkingLotOrder = parkingLotOrderService.park(car);
            return ResponseEntity.ok(parkingLotOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

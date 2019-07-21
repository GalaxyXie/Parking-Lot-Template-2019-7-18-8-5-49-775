package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.Car;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import com.thoughtworks.parking_lot.sevice.ParkingLotOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotOrderController {
    @Autowired
    private ParkingLotOrderService parkingLotOrderService;

    @PostMapping("/parkinglot-orders/parkingLot/{id}")
    public ResponseEntity createOrder(@RequestBody Car car,@PathVariable int id) {
        try {
            ParkingLotOrder parkingLotOrder = parkingLotOrderService.park(car,id);
            return ResponseEntity.ok(parkingLotOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/parkinglot-orders/{id}")
    public ResponseEntity updateOrder(@PathVariable int id) throws Exception{
        return ResponseEntity.ok(parkingLotOrderService.fetch(id));
    }

}

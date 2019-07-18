package com.thoughtworks.parking_lot.sevice.impl;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.sevice.ParkingLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotsServiceImpl implements ParkingLostService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ResponseEntity DeleteParkingLot( int index){
        parkingLotRepository.deleteById(index);
        List<ParkingLot>parkingLots=parkingLotRepository.findAll();
        return ResponseEntity.ok(parkingLots);
    }
    public ResponseEntity createParkingLost(ParkingLot parkingLot){
        parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(parkingLot);
    }
    public ResponseEntity FindParkingLotsByPage(int Page){
        int pagesize=15;
        return ResponseEntity.ok(parkingLotRepository.findAll().stream()
                .skip((Page-1)*pagesize)
                .limit(pagesize));
    }
    public ResponseEntity FindParkingLotById(int Id){
        return ResponseEntity.ok(parkingLotRepository.findById(Id));
    }
    public ResponseEntity UpdateParkingLotsById(int Id,int Capacity){
        ParkingLot parkingLot=parkingLotRepository.findById(Id).get();
        parkingLot.setCapacity(Capacity);
        return ResponseEntity.ok(parkingLot);

    }
}

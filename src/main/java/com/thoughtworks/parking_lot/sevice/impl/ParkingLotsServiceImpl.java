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
@Service
public class ParkingLotsServiceImpl implements ParkingLostService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    public ResponseEntity DeleteCompany( int index){
        parkingLotRepository.deleteById(index);
        List<ParkingLot>parkingLots=parkingLotRepository.findAll();
        return ResponseEntity.ok(parkingLots);
    }
    public ResponseEntity createParkingLost(ParkingLot parkingLot){
        parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(parkingLot);
    }
}

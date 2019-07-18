package com.thoughtworks.parking_lot.sevice;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
@Service
public interface ParkingLostService {
    public ResponseEntity DeleteParkingLot(@PathVariable int index);
    public ResponseEntity createParkingLost(ParkingLot parkingLot);
    public ResponseEntity FindParkingLotsByPage(int Page);
}

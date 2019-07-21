package com.thoughtworks.parking_lot.sevice;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
@Service
public interface ParkingLostService {

    public ParkingLot createParkingLost(ParkingLot parkingLot);
    public void DeleteParkingLot( int index);
    public ResponseEntity FindParkingLotsByPage(int Page);
    public ResponseEntity FindParkingLotById(int id);
    public ResponseEntity UpdateParkingLotsById(int Id,int Capacity);
}

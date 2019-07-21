package com.thoughtworks.parking_lot.sevice;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface ParkingLostService {

    public ParkingLot createParkingLost(ParkingLot parkingLot);
    public void DeleteParkingLot( int index);
    public  List<ParkingLot> FindParkingLotsByPage(int Page);
    public ParkingLot FindParkingLotById(int Id);
    public ResponseEntity UpdateParkingLotsById(int Id,int Capacity);
}

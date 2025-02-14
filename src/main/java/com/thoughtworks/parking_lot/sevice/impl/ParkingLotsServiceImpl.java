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
import java.util.stream.Collectors;

@Service
public class ParkingLotsServiceImpl implements ParkingLostService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;


    public ParkingLot createParkingLost(ParkingLot parkingLot){

        return parkingLotRepository.save(parkingLot);
    }
    public void DeleteParkingLot( int index){
        parkingLotRepository.deleteById(index);
    }
    public List<ParkingLot> FindParkingLotsByPage(int Page){
        int pagesize=15;
        return parkingLotRepository.findAll().stream()
                .skip((Page-1)*pagesize)
                .limit(pagesize).collect(Collectors.toList());
    }
    public ParkingLot FindParkingLotById(int Id){

        return parkingLotRepository.findById(Id).get();
    }
    public ParkingLot UpdateParkingLotCapacityById(int Id,ParkingLot parkingLot){
        ParkingLot oldParkingLot=parkingLotRepository.findById(Id).get();
        oldParkingLot.setCapacity(parkingLot.getCapacity());
        parkingLotRepository.save(oldParkingLot);
        return oldParkingLot;

    }
    public ParkingLot GetParkingLotHasParkingSpace(){

        List<ParkingLot>parkingLots=parkingLotRepository.findAll();
        ParkingLot parkingLot=parkingLots.stream().filter(parkingLotNotFull ->
                parkingLotNotFull.getParkingPlace()>0)
                .findFirst()
                .orElse(null);
        return  parkingLot;
    }
}

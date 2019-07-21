package com.thoughtworks.parking_lot.sevice;

import com.thoughtworks.parking_lot.model.Car;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import org.springframework.stereotype.Service;

@Service
public interface ParkingLotOrderService {
    public ParkingLotOrder park(Car car,int Id) throws Exception;
    public ParkingLotOrder fetch(int Id) throws Exception;
}

package com.thoughtworks.parking_lot.sevice.impl;

import com.thoughtworks.parking_lot.model.Car;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotOrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.sevice.ParkingLostService;
import com.thoughtworks.parking_lot.sevice.ParkingLotOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParkingLotOrderServiceImpl implements ParkingLotOrderService {
    @Autowired
    private ParkingLotOrderRepository parkingLotOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLotOrder park(Car car,int id)throws Exception{
        ParkingLot parkingLot=parkingLotRepository.findById(id).orElse(null);
        if (parkingLot.getParkingPlace()<=0)
            throw new Exception("停车场已经满");
        else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            ParkingLotOrder parkingLotOrder=new ParkingLotOrder(parkingLot.getName(),car.getCarNumber(),date);
            parkingLotOrderRepository.save(parkingLotOrder);
            return parkingLotOrder;
        }
    }
    public ParkingLotOrder fetch(int Id){
        ParkingLotOrder parkingLotOrder=parkingLotOrderRepository.findById(Id).get();
        parkingLotOrder.setStatus(false);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        parkingLotOrder.setLeaveTime(date);
        return parkingLotOrderRepository.save(parkingLotOrder);
    }
}

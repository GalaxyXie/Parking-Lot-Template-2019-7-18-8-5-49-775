package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotOrderRepository extends JpaRepository<ParkingLotOrder,Integer> {
}

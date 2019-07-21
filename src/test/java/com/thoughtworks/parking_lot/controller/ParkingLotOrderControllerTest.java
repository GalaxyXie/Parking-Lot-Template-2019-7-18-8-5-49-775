package com.thoughtworks.parking_lot.controller;

import com.google.gson.Gson;
import com.thoughtworks.parking_lot.model.Car;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingLotOrder;

import com.thoughtworks.parking_lot.sevice.ParkingLostService;
import com.thoughtworks.parking_lot.sevice.ParkingLotOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.text.SimpleDateFormat;
import java.util.Date;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ParkingLotOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotOrderService parkingLotOrderService;
    @MockBean
    private ParkingLostService parkingLostService;
    @Test
    void should_return_the_status_when_create_a_ParkingLostOrderSuccess() throws Exception {
        Car car = new Car("湘DX8888");
        ParkingLot parkingLot=new ParkingLot("Laura's Parking lot",10,"here");
        parkingLot.setId(1);parkingLot.setParkingPlace(10);
        parkingLostService.createParkingLost(parkingLot);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        ParkingLotOrder parkingLotOrder = new ParkingLotOrder("Laura's Parking lot", car.getCarNumber(),date);
        parkingLotOrder.setId(1);

        when(parkingLotOrderService.park(car,1)).thenReturn(parkingLotOrder);

        mockMvc.perform(post("/parkinglot-orders/parkingLot/1")
                .content(new Gson().toJson(car)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void should_return_the_wrongMessage_when_create_a_ParkingLostOrderSuccess() throws Exception {
        Car car = new Car("湘DX8888");
        ParkingLot parkingLot=new ParkingLot("Laura's Parking lot",10,"here");
        parkingLot.setId(1);parkingLot.setParkingPlace(0);
        parkingLostService.createParkingLost(parkingLot);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        ParkingLotOrder parkingLotOrder = new ParkingLotOrder("Laura's Parking lot", car.getCarNumber(),date);
        parkingLotOrder.setId(1);

        when(parkingLotOrderService.fetch(1)).thenThrow(new Exception("停车场已经满"));

        mockMvc.perform(post("/parkinglot-orders/parkingLot/1")
                .content(new Gson().toJson(car)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        .andExpect(content().string("停车场已经满"));
    }
    @Test
    void should_return_the_order_when_fetch_a_car() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Car car = new Car("湘DX8888");
        ParkingLotOrder parkingLotOrder = new ParkingLotOrder("Laura's Parking lot", car.getCarNumber(),date);
        parkingLotOrder.setId(1);
        ParkingLot parkingLot=new ParkingLot("Laura's Parking lot",10,"here");
        parkingLot.setId(1);
        ParkingLotOrder newParkingLotOrder = parkingLotOrder;
        newParkingLotOrder.setStatus(false);
        given(parkingLotOrderService.fetch(1))
                .willReturn(parkingLotOrder);

        mockMvc.perform(put("/parkinglot-orders/1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
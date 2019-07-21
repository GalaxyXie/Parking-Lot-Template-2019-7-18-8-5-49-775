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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ParkingLotOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotOrderService parkingLotOrderService;

    @Test
    void should_return_the_status_when_create_a_ParkingLostOrderSuccess() throws Exception {
        Gson gson = new Gson();
        Car car=new Car("湘DAX888");
        ParkingLot parkingLot = new ParkingLot("Laura'sParkingLot",25,"zhuhai");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        ParkingLotOrder parkingLotOrder=new ParkingLotOrder(parkingLot.getName(),car.getCarNumber(),date);

        String result=gson.toJson(parkingLot);

        when(parkingLotOrderService.park(car)).thenReturn(parkingLotOrder);
        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isOk());
    }

}
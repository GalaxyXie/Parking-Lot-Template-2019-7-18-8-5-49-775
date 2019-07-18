package com.thoughtworks.parking_lot.controller;



import com.google.gson.Gson;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.AutoConfigureDataNeo4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)

@SpringBootTest
@AutoConfigureMockMvc
class ParkingLotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @Test
    void should_return_the_parkingLot_when_create_a_ParkingLost() throws Exception {
        Gson gson = new Gson();

        ParkingLot parkingLot = new ParkingLot("Laura'sParkingLot",25,"zhuhai");
        String result=gson.toJson(parkingLot);
        Mockito.when(parkingLotRepository.save(parkingLot)).thenReturn(parkingLot);
        mockMvc.perform(post("/parking-lots").contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(result));
    }

}
package com.thoughtworks.parking_lot.controller;



import com.google.gson.Gson;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
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
    @Test
    void should_return_the_parkingLot_List_when_delete_a_ParkingLost() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot1 = new ParkingLot("Zhou'sParkingLot",35,"changsha");
        ParkingLot parkingLot2 = new ParkingLot("Laura'sParkingLot",25,"zhuhai");
        String result=gson.toJson(parkingLot1);
        String result2=gson.toJson(parkingLot2);
        parkingLotRepository.save(parkingLot1);
        parkingLotRepository.save(parkingLot2);
        List<ParkingLot>parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot2);
        mockMvc.perform(delete("/parking-lots/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(content().json(gson.toJson(parkingLots, List.class)));
    }
    @Test
    void should_return_the_parkingLot_List_when_find_ParkingLots_by_Page() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot1 = new ParkingLot("Zhou'sParkingLot",35,"changsha");
        ParkingLot parkingLot2 = new ParkingLot("Laura'sParkingLot",25,"zhuhai");
        String result=gson.toJson(parkingLot1);
        String result2=gson.toJson(parkingLot2);
        mockMvc.perform(post("/parking-lots").contentType(MediaType.APPLICATION_JSON)
                .content(result));
        mockMvc.perform(post("/parking-lots").contentType(MediaType.APPLICATION_JSON)
                .content(result2));
//        System.out.println(parkingLotRepository.findAll().size());
        List<ParkingLot>parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        mockMvc.perform(get("/parking-lots?page=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(gson.toJson(parkingLots, List.class)));
    }

}
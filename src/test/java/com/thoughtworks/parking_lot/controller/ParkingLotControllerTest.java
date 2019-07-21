package com.thoughtworks.parking_lot.controller;



import com.google.gson.Gson;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.sevice.ParkingLostService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.AutoConfigureDataNeo4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ParkingLotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLostService parkingLostService;

    @Test
    void should_return_the_parkingLot_when_create_a_ParkingLost() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot = new ParkingLot("Laura'sParkingLot",25,"zhuhai");
        parkingLot.setId(1);
        String result=gson.toJson(parkingLot);
        when(parkingLostService.createParkingLost(parkingLot)).thenReturn(parkingLot);
        mockMvc.perform(post("/parking-lots").contentType(MediaType.APPLICATION_JSON)
                .content(result))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_the_status_when_delete_a_ParkingLost_success() throws Exception {
        mockMvc.perform(delete("/parking-lots/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    void should_return_the_parkingLot_List_when_find_ParkingLots_by_Page() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>(20);
        parkingLots.add(new ParkingLot("Laura'sParkingLot",25,"zhuhai"));

        given(parkingLostService.FindParkingLotsByPage(1)).willReturn(parkingLots);

        mockMvc.perform(get("/parking-lots")
                .param("page", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(parkingLots,List.class)));
    }
    /*
    @Test
    void should_return_the_parkingLot_when_find_ParkingLots_by_ID() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot1 = new ParkingLot("Zhou'sParkingLot",35,"changsha");
        ParkingLot parkingLot2 = new ParkingLot("Laura'sParkingLot",25,"zhuhai");
        parkingLot1.setId(1);
        parkingLot2.setId(2);
        List<ParkingLot>parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        String result = gson.toJson(parkingLot1);
        when(parkingLotRepository.findById(1).get()).thenReturn(parkingLot1);

        mockMvc.perform(get("/parking-lots/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(result));
    }

    @Test
    void should_return_the_parkingLot_when_update_ParkingLots_by_ID() throws Exception {
        Gson gson = new Gson();
        ParkingLot parkingLot1 = new ParkingLot("Zhou'sParkingLot",35,"changsha");
        ParkingLot parkingLot2 = new ParkingLot("Zhou'sParkingLot",25,"changsha");
        parkingLot1.setId(1);
        parkingLot2.setId(2);
        List<ParkingLot>parkingLots=new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        String result = gson.toJson(parkingLot1);
        when(parkingLotRepository.findById(1).get()).thenReturn(parkingLot1);

        mockMvc.perform(get("/parking-lots/1/capacity/{capacity}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(result));
    }
*/
}
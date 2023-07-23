package com.vnxua.reservation;

import com.vnxua.reservation.entity.MyTable;
import com.vnxua.reservation.repository.MyTableRepository;
import com.vnxua.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(classes = VietNamXuaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RestControllerTest {
    @Autowired
    MockMvc mvc;
    @Mock
    MyTableRepository myTableRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Autowired
    private WebApplicationContext context;

    @Test
    @WithMockUser(username = "ngon", password = "1", roles = "ADMIN")
    public void TestFindAllTable() throws Exception {

        List<MyTable> myTables = myTableRepository.findAll();

        given(myTableRepository.findAll()).willReturn(myTables);

        mvc.perform(MockMvcRequestBuilders.get("/table/gettall")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(13));
//                .andDo(print());
    }

    @Test
    @WithMockUser(username = "ngonc", password = "1", roles = "ADMIN")
    public void TestGetReservationById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/reservation/getreserbyid/10041")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }
}

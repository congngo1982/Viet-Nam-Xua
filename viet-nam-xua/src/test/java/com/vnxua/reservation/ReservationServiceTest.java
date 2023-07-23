package com.vnxua.reservation;

import com.vnxua.reservation.service.IReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    IReservationService iReservationService;
    @Test
    public void GetReservationById(){
        Assert.isTrue(iReservationService.GetReservationById(10060).get(0).getAccount().getUsername().equals("ngoo"));
    }

    @Test
    public void GetFilterReservation(){

    }

    @Test
    public void GetCurrentReservation(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Assert.isTrue(iReservationService.GetCurrentReservation("cnngo", Timestamp.valueOf(localDateTime.toString())).getId() == 1);
    }
}

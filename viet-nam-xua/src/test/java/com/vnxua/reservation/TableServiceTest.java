package com.vnxua.reservation;

import com.vnxua.reservation.repository.AccountRepository;
import com.vnxua.reservation.repository.MyTableRepository;
import com.vnxua.reservation.service.ITableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest
public class TableServiceTest {
    @Autowired
    ITableService iTableService;

    @Test
    public void GetAllTable(){

    }

    @Test
    public void GetTableById(){

    }

    @Test
    public void GetAvailableTable(){

    }
}

package com.vnxua.reservation;

import com.vnxua.reservation.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    IAccountService iAccountService;
    @Test
    public void GetAccountByUsername() {
        Assert.isTrue(iAccountService.GetAccountByUsername("ngoo").getUsername().contains("ngoo"));
    }
}

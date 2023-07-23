package com.vnxua.reservation.controller;

import com.vnxua.reservation.entity.Account;
import com.vnxua.reservation.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    @Autowired
    private IAccountService iAccountService;

    @GetMapping("/getaccount")
    public List<Account> GettAllAccount() {
        return iAccountService.GetAllAccount();
    }

    @PostMapping("/createstaff")
    public Account UpdateAccount(@RequestBody String username) {
//        String username = objectNode.get("username").asText();
        System.out.println(username);
        Account account = iAccountService.GetAccountByUsername(username);
        if (account != null) {
            account.setRole("STAFF");
            iAccountService.CreateAccount(account);
        }
        return account;
    }

}

package com.vnxua.reservation.controller;

import com.vnxua.reservation.entity.Account;
import com.vnxua.reservation.securiry.Bcrypt;
import com.vnxua.reservation.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountRestController {
    @Autowired
    private IAccountService iAccountService;

    @GetMapping("/getaccount")
    public Account GetAccount() {
        return iAccountService.GetAccountByUsername("ngonc");
    }

    @PostMapping("/createaccount")
    public String AddAccount(@RequestBody Account account) {
        Account account1 = null;
        if (iAccountService.GetAccountByUsername(account.getUsername()) == null) {
            account.setPassword(Bcrypt.hashPassword(account.getPassword()));
            account1 = iAccountService.CreateAccount(account);
        }
        String result = account1 != null ? "Success" : "Failed";
        return result;
    }

    @GetMapping("/getuser")
    public Account currentUserName(Authentication authentication) {
        Account account = null;
        if (authentication != null) {
            String username = authentication.getName();
            if (username != null) {
                account = iAccountService.GetAccountByUsername(username);
            }
        }
        return account;
    }

    @PutMapping("/changepassword/{password}")
    public void UpdatePassword(@PathVariable("password") String password, Authentication authentication) {
        System.out.println(authentication);
        if (authentication != null) {
            iAccountService.UpdateAccount(Bcrypt.hashPassword(password), authentication.getName());
        }
    }
}

package com.vnxua.reservation.service;

import com.vnxua.reservation.entity.Account;
import com.vnxua.reservation.repository.AccountRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class AccountService implements IAccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account GetAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Account CreateAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    @Override
    public List<Account> GetAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void UpdateAccount(String password, String username) {
        accountRepository.UpdateAccount(password, username);
    }
}

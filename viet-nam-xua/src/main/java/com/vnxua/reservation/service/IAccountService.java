package com.vnxua.reservation.service;

import com.vnxua.reservation.entity.Account;

import java.util.List;

public interface IAccountService {
public Account GetAccountByUsername(String username);
public Account CreateAccount(Account account);
public List<Account> GetAllAccount();
public void UpdateAccount(String password, String username);
}

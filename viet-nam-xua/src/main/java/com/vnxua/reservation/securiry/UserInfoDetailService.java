package com.vnxua.reservation.securiry;

import com.vnxua.reservation.entity.Account;
import com.vnxua.reservation.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDetailService implements UserDetailsService {

    @Autowired
    private IAccountService iAccountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountService.GetAccountByUsername(username);
        UserDetails userDetails = User.builder().username(username)
                .password(account.getPassword())
                .roles(account.getRole().substring(0))
                .build();
        System.out.println(account.getRole());
        System.out.println(userDetails.getAuthorities());
        return userDetails;
    }
}

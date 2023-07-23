package com.vnxua.reservation.repository;

import com.vnxua.reservation.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "SELECT account FROM Account account WHERE account.status = true AND account.username = :username")
    public Account findAccountByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Account account SET account.password = :password WHERE account.username = :username")
    public void UpdateAccount(@Param("password") String password, @Param("username") String username);
}

package com.vnxua.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private boolean status;
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Reservation> myReservation;

    public Account(String username, String password, String fullName, String role, boolean status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
    }

    public Account(String username, String password, String fullName, String email, String phone, String role, boolean status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }
}

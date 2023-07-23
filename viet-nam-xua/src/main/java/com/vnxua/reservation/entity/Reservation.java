package com.vnxua.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdDate;
    private int status;
    private int quantity;
    private Date createdTime;
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reservation_table", joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id"))
    @JsonIgnore
    private Set<MyTable> myTables;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", createdTime=" + createdTime +
                ", note='" + note + '\'' +
                ", account=" + account +
                ", myTables=" + myTables +
                '}';
    }
}

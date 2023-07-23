package com.vnxua.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MyTable {
    @Id
    private String id;
    private int quantity;
    private int status;

    @ManyToMany(mappedBy = "myTables")
    @JsonIgnore
    private Set<Reservation> reservation;

    @Override
    public String toString() {
        return "MyTable{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                ", reservation=" + reservation +
                '}';
    }
}

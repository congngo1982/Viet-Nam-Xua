package com.vnxua.reservation.repository;

import com.vnxua.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> getReservationsByAccount_Username(String username);

    public List<Reservation> getReservationsById(long id);

    @Query(value = "SELECT reser FROM Reservation reser " +
            "JOIN reser.myTables myTable " +
            "WHERE myTable.id = :tableId AND cast(reser.createdDate as date ) = cast(:date as date ) AND reser.status = :status")
    public List<Reservation> GetFilterReservation(@Param("tableId") String tableId, @Param("date") Date date, @Param("status") int status);

    @Query(value = "SELECT reser from Reservation  reser WHERE reser.status = 2")
    public List<Reservation> GetSuccessfulReservation();

    @Query(value = "SELECT reser from Reservation  reser WHERE cast(reser.createdDate as date ) = cast(:date as date ) AND reser.account.username = :username AND reser.status = 1")
    public Reservation GetCurrentReservation(@Param("username") String username, @Param("date") Date date);

    @Modifying
    @Transactional
    @Query(value = "update Reservation  reser set reser.status = :status where reser.id = :id")
    public void UpdateReservation(@Param("id") long id, @Param("status") int status);
}

package com.vnxua.reservation.service;

import com.vnxua.reservation.entity.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IReservationService {
    public List<Reservation> GetReservationByAccount(String username);
    public List<Reservation> GetReservationById(long id);
    public Reservation CreateReservation(Reservation reservation);
    public List<Reservation> GetFilterReservation(String tableId, Date date, int status);
    public List<Reservation> GetSuccessfulReservation();
    public Reservation GetCurrentReservation(String username, Date date);

    public void UpdateReservation(long id, int status);
}

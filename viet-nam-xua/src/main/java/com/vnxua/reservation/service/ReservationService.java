package com.vnxua.reservation.service;

import com.vnxua.reservation.entity.Reservation;
import com.vnxua.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public List<Reservation> GetReservationByAccount(String username) {
        return reservationRepository.getReservationsByAccount_Username(username);
    }


    @Override
    public List<Reservation> GetReservationById(long id) {
        return reservationRepository.getReservationsById(id);
    }

    @Override
    public Reservation CreateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> GetFilterReservation(String tableId, Date date, int status) {
        System.out.println("Created: " + date);
        return reservationRepository.GetFilterReservation(tableId, date, status);
    }

    @Override
    public List<Reservation> GetSuccessfulReservation() {
        return reservationRepository.GetSuccessfulReservation();
    }

    @Override
    public Reservation GetCurrentReservation(String username, Date date) {
        return reservationRepository.GetCurrentReservation(username, date);
    }

    @Override
    public void UpdateReservation(long id, int status) {
        reservationRepository.UpdateReservation(id, status);
    }

}

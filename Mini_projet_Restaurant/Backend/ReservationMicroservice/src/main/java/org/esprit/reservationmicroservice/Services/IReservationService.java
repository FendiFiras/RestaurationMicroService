package org.esprit.reservationmicroservice.Services;

import org.esprit.reservationmicroservice.Entities.Reservation;

import java.io.IOException;

public interface IReservationService {
        Reservation createReservation(Reservation reservation);

        Iterable<Reservation> getAllReservations();

        void deleteReservation(Long id);

        Reservation updateReservation(Long id, Reservation updatedReservation);
        byte[] exportReservationsToExcel() throws IOException;

         Reservation markAsUrgent(Long id);
         boolean isUrgent(Reservation reservation);

        public Iterable<Reservation> getUrgentReservations();

        }

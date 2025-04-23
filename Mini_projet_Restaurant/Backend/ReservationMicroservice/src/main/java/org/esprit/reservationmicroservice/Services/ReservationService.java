package org.esprit.reservationmicroservice.Services;

import lombok.RequiredArgsConstructor;
import org.esprit.reservationmicroservice.Clients.UserClient;
import org.esprit.reservationmicroservice.Entities.Reservation;
import org.esprit.reservationmicroservice.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.esprit.reservationmicroservice.Dto.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {


    @Autowired
    private UserClient userClient;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ExportService exportService;

    @Override
    public Reservation createReservation(Reservation reservation) {
        User user = userClient.getUtilisateur(reservation.getUserId());
        reservation.setAdditionalDetails("Réservé par " + user.getNom());
        return reservationRepository.save(reservation);
    }

    @Override
    public Iterable<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation existing = optionalReservation.get();
            existing.setUserId(updatedReservation.getUserId());
            existing.setNumberOfGuests(updatedReservation.getNumberOfGuests());
            existing.setEventDate(updatedReservation.getEventDate());
            existing.setEventTime(updatedReservation.getEventTime());
            existing.setTimeFlexible(updatedReservation.isTimeFlexible());
            existing.setEventType(updatedReservation.getEventType());
            existing.setEventFormat(updatedReservation.getEventFormat());

            User user = userClient.getUtilisateur(updatedReservation.getUserId());
            existing.setAdditionalDetails("Réservé par " + user.getNom());

            return reservationRepository.save(existing);
        } else {
            return null;
        }
    }

    @Override
    public byte[] exportReservationsToExcel() throws IOException {
        List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
        return exportService.exportReservationsToExcel(reservations).toByteArray();
    }


    @Override
    public Reservation markAsUrgent(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            if (isUrgent(reservation)) {
                System.out.println("Réservation marquée comme urgente : " + reservation.getIdReservation());
            }

            return reservation;
        } else {
            return null;
        }
    }

    @Override
    public boolean isUrgent(Reservation reservation) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eventDateTime = LocalDateTime.of(reservation.getEventDate(), reservation.getEventTime());
        return eventDateTime.isBefore(now.plusHours(2));
    }

    @Override
    public Iterable<Reservation> getUrgentReservations() {
        Iterable<Reservation> allReservations = reservationRepository.findAll();
        List<Reservation> urgentReservations = new ArrayList<>();

        for (Reservation reservation : allReservations) {
            if (isUrgent(reservation)) {
                urgentReservations.add(reservation);
            }
        }

        return urgentReservations;
    }
}

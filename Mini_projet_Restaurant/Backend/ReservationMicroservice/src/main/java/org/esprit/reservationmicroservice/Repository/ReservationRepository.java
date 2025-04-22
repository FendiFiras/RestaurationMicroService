package org.esprit.reservationmicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.esprit.reservationmicroservice.Entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Tu peux ajouter des méthodes personnalisées ici
}





    package org.esprit.reservationmicroservice.Services;

    import lombok.RequiredArgsConstructor;
    import org.esprit.reservationmicroservice.Entities.Reservation;
    import org.esprit.reservationmicroservice.Repository.ReservationRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.esprit.reservationmicroservice.Dto.User;  // Import du DTO User

    import java.io.IOException;
    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class ReservationService implements IReservationService{
        @Autowired
        private  ReservationRepository reservationRepository;
        @Autowired
        private  UserClientFake userClientFake; // Injecté automatiquement
        @Autowired
        private  ExportService exportService;

        public Reservation createReservation(Reservation reservation) {
            String userName = userClientFake.getNomUtilisateur(reservation.getUserId());
            reservation.setAdditionalDetails("Réservé par " + userName);
            return reservationRepository.save(reservation);
        }

        public Iterable<Reservation> getAllReservations() {
            return reservationRepository.findAll();
        }

        public void deleteReservation(Long id) {
            reservationRepository.deleteById(id);
        }

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

                String userName = userClientFake.getNomUtilisateur(updatedReservation.getUserId());
                existing.setAdditionalDetails("Réservé par " + userName);

                return reservationRepository.save(existing);
            } else {
                return null;
            }
        }
        // Méthode pour exporter les réservations en fichier Excel
        public byte[] exportReservationsToExcel() throws IOException {
            List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
            return exportService.exportReservationsToExcel(reservations).toByteArray();
        }



        // Méthode pour marquer une réservation comme urgente (logique métier, pas en base de données)
        public Reservation markAsUrgent(Long id) {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            if (optionalReservation.isPresent()) {
                Reservation reservation = optionalReservation.get();

                // Définir la logique pour marquer la réservation comme urgente
                if (isUrgent(reservation)) {
                    // Traitement spécifique pour les réservations urgentes
                    System.out.println("Réservation marquée comme urgente : " + reservation.getIdReservation());
                    // Tu peux appliquer ici une logique de priorité, d'envoi de notifications, etc.
                }

                return reservation; // Retourner la réservation sans modification dans la base
            } else {
                return null; // Gérer un cas d'erreur ou de non-trouvabilité
            }
        }

        // Exemple de logique pour déterminer si une réservation est urgente
        public boolean isUrgent(Reservation reservation) {
            // Exemple : considérer qu'une réservation est urgente si elle est faite moins de 2 heures avant l'événement
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime eventDateTime = LocalDateTime.of(reservation.getEventDate(), reservation.getEventTime());
            return eventDateTime.isBefore(now.plusHours(2)); // Si l'événement est dans moins de 2 heures
        }

        // Méthode pour récupérer toutes les réservations urgentes (logique métier)
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

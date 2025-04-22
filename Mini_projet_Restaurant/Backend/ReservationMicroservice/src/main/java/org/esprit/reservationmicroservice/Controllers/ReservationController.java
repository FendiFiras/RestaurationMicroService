package org.esprit.reservationmicroservice.Controllers;

import lombok.RequiredArgsConstructor;
import org.esprit.reservationmicroservice.Entities.Reservation;
import org.esprit.reservationmicroservice.Services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private  IReservationService reservationService;

    // ➕ Ajouter une réservation
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // 📋 Obtenir toutes les réservations
    @GetMapping("/all")
    public Iterable<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // ❌ Supprimer une réservation
    @DeleteMapping("/delete-reservation/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

    // ✏️ Modifier une réservation
    @PutMapping("/update-reservation/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportReservationsToExcel() throws IOException {
        byte[] excelBytes = reservationService.exportReservationsToExcel();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservations.xlsx")
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(excelBytes);
    }



    // Marquer une réservation comme urgente
    @PutMapping("/mark-urgent/{id}")
    public ResponseEntity<String> markAsUrgent(@PathVariable Long id) {
        Reservation reservation = reservationService.markAsUrgent(id);
        if (reservation != null) {
            return ResponseEntity.ok("Réservation marquée comme urgente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Réservation non trouvée");
        }
    }

    // Obtenir toutes les réservations urgentes
    @GetMapping("/urgent")
    public Iterable<Reservation> getUrgentReservations() {
        return reservationService.getUrgentReservations();
    }
}

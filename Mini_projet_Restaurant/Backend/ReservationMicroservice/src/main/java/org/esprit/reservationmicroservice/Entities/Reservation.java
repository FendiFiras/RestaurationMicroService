package org.esprit.reservationmicroservice.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private Long userId; // ✅ IMPORTANT : Ce champ doit exister
    private int numberOfGuests;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private boolean isTimeFlexible;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private EventFormat eventFormat;

    @Column(length = 1000)
    private String additionalDetails;
}

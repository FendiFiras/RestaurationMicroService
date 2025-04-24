package tn.esprit.livraisonmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private String destination;
    private String status;
    private LocalDate deliveryDate;

  

    private Double latitude;

    private Double longitude;


    private String recipientName;
    private String deliveryAgent;
}

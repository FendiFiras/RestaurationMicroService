package org.esprit.commandemicroservice.Entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    @Temporal(TemporalType.DATE)
    private Date dateCommande;

    private String statut;
    private Double total;

    private Long idUser; // Représente un utilisateur distant (microservice User)
    private Long idLivraison;  // Livraison (via livraison-service)

    @ElementCollection
    private List<Long> idPlats; // Liste des plats (via menu-service)

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;
    @Enumerated(EnumType.STRING)
    private TypeCommande typeCommande; // ✅ ICI le nouveau champ


    private String numeroCommande;
    @JsonIgnore

    @Transient
    private String nomUser;
    @JsonIgnore

    @Transient
    private List<String> nomsPlats;
    @JsonIgnore

    @Transient
    private String nomLivreur;

}

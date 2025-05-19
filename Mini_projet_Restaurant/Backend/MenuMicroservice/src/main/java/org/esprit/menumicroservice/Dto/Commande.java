package org.esprit.menumicroservice.Dto;

import lombok.Data;

import java.util.Date;
import lombok.Data;

@Data

public class Commande {

    private Long idCommande;
    private Date dateCommande;
    private String statut;
    private Double total;

    public Long getIdCommande() {
        return idCommande;
    }
}
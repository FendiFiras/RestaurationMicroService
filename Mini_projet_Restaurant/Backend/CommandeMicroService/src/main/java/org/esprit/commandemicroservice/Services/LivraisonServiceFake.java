package org.esprit.commandemicroservice.Services;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LivraisonServiceFake {

    private static final Map<Long, String> fakeLivreurs = new HashMap<>();

    static {
        fakeLivreurs.put(10L, "Ahmed Le Livreur");
        fakeLivreurs.put(11L, "Sana Livraison Express");
    }

    public String getNomLivreur(Long idLivraison) {
        return fakeLivreurs.getOrDefault(idLivraison, "Livreur inconnu");
    }

    public String getDateEstimeeLivraison(Long idLivraison) {
        return "2025-04-12 13:30"; // tu peux simuler avec la date du jour + 1h
    }
}
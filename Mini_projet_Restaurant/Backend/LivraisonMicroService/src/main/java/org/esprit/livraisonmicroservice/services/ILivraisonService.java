package org.esprit.Livraisonmicroservice.services;

import org.esprit.Livraisonmicroservice.entities.Livraison;

import java.util.List;

public interface ILivraisonService {
    List<Livraison> getAllLivraisons();
    Livraison getLivraisonById(Long id);
    Livraison addLivraison(Livraison livraison);
    Livraison updateLivraison(Long id, Livraison livraison);
    void deleteLivraison(Long id);
}
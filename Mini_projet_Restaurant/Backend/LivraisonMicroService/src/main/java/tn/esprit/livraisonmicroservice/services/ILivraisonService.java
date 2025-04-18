package tn.esprit.livraisonmicroservice.services;

import tn.esprit.livraisonmicroservice.entities.Livraison;

import java.util.List;

public interface ILivraisonService {
    List<Livraison> getAllLivraisons();
    Livraison getLivraisonById(Long id);
    Livraison addLivraison(Livraison livraison);
    Livraison updateLivraison(Long id, Livraison livraison);
    void deleteLivraison(Long id);
}
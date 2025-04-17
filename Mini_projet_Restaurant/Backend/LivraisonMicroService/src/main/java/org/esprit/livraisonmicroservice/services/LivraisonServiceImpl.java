package org.esprit.Livraisonmicroservice.services;

import org.esprit.Livraisonmicroservice.entities.Livraison;
import org.esprit.Livraisonmicroservice.repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivraisonServiceImpl implements ILivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Override
    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    @Override
    public Livraison getLivraisonById(Long id) {
        return livraisonRepository.findById(id).orElse(null);
    }

    @Override
    public Livraison addLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    @Override
    public Livraison updateLivraison(Long id, Livraison livraison) {
        livraison.setId(id);
        return livraisonRepository.save(livraison);
    }

    @Override
    public void deleteLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }
}
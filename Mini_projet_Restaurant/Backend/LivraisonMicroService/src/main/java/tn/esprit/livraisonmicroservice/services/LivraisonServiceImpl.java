package tn.esprit.livraisonmicroservice.services;

import tn.esprit.livraisonmicroservice.Clients.UserClient;
import tn.esprit.livraisonmicroservice.dto.User;
import tn.esprit.livraisonmicroservice.entities.Livraison;
import tn.esprit.livraisonmicroservice.repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivraisonServiceImpl implements ILivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;
    private UserClient userClient; // Injection du client Feign

    @Override
    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    @Override
    public Livraison getLivraisonById(Long id) {
        Livraison livraison = livraisonRepository.findById(id).orElse(null);
        if (livraison != null && livraison.getIdUser() != null) {
            try {
                User user = userClient.getUtilisateur(livraison.getIdUser());
                System.out.println("Utilisateur associé : " + user.getNom());
                // Optionnel : tu pourrais ici enrichir l'objet Livraison ou retourner un DTO combiné
            } catch (Exception e) {
                System.err.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            }
        }
        return livraison;
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
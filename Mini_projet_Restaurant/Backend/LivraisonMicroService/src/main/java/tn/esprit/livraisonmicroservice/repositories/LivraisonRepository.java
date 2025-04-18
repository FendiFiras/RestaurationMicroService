package tn.esprit.livraisonmicroservice.repositories;

import tn.esprit.livraisonmicroservice.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
}
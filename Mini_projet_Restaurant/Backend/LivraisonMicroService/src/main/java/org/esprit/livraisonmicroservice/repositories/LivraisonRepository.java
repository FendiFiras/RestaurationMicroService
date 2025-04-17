package org.esprit.Livraisonmicroservice.repositories;

import org.esprit.Livraisonmicroservice.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
}
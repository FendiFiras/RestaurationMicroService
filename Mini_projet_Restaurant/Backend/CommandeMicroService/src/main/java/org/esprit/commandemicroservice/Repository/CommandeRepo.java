package org.esprit.commandemicroservice.Repository;
import org.esprit.commandemicroservice.Entites.Commande;
import org.esprit.commandemicroservice.Entites.ModePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepo extends JpaRepository<Commande, Long> {

    List<Commande> findAllByOrderByTotalAsc();
    List<Commande> findAllByOrderByTotalDesc();

    @Query("SELECT c FROM Commande c WHERE (:modePaiement IS NULL OR c.modePaiement = :modePaiement)")
    List<Commande> findByModePaiement(@Param("modePaiement") ModePaiement modePaiement);

}



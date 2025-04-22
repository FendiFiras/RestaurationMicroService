package org.esprit.avismicroservice.Repository;

import org.esprit.avismicroservice.Entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository  extends JpaRepository<Avis, Long> {
    List<Avis> findByCommentaireContaining(String commentaire);

    List<Avis> findByCommentaireContainingAndNote(String commentaire, Integer note);
}

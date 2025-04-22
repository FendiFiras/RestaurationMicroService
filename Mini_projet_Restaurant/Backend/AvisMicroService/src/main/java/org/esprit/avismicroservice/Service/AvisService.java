package org.esprit.avismicroservice.Service;

import org.esprit.avismicroservice.Entity.Avis;
import org.esprit.avismicroservice.Repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisService {
    @Autowired
    private AvisRepository avisRepository;

    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    public Optional<Avis> getAvisById(Long id) {
        return avisRepository.findById(id);
    }

    public Avis createAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    public Avis updateAvis(Long id, Avis updatedAvis) {
        return avisRepository.findById(id).map(avis -> {
            avis.setNote(updatedAvis.getNote());
            avis.setCommentaire(updatedAvis.getCommentaire());
            return avisRepository.save(avis);
        }).orElse(null);
    }

    public boolean deleteAvis(Long id) {
        if (avisRepository.existsById(id)) {
            avisRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Avis> searchReviews(String keyword, Integer rating) {
        if (rating != null) {
            return avisRepository.findByCommentaireContainingAndNote(keyword, rating);
        } else {
            return avisRepository.findByCommentaireContaining(keyword);
        }
    }

}

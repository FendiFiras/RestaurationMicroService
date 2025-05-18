package org.esprit.avismicroservice.Service;

import feign.FeignException;
import org.esprit.avismicroservice.Client.UserClient;
import org.esprit.avismicroservice.Dto.User;
import org.esprit.avismicroservice.Entity.Avis;
import org.esprit.avismicroservice.Repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvisService {
    @Autowired
    private AvisRepository avisRepository;
    @Autowired
    private UserClient userClient;


    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    public Optional<Avis> getAvisById(Long id) {
        return avisRepository.findById(id);
    }

    public Avis createAvis(Avis avis) {
        // 🔍 Vérifier l'existence de l'utilisateur AVANT de créer un avis
        validateUserExists(avis.getIdUser());

        // ✅ Enregistrer l'avis si l'utilisateur existe
        return avisRepository.save(avis);
    }

    private void validateUserExists(Long idUser) {
        try {
            userClient.getUtilisateur(idUser);
        } catch (FeignException e) {
            System.out.println("❌ Feign error status: " + e.status());
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new RuntimeException("User doesn't exist");
            }
            throw new RuntimeException("User service error: " + e.getMessage());
        }
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
    public Map<String, Object> getAvisStatistics() {
        List<Integer> notes = avisRepository.findAllNotes();

        long total = notes.size();
        double average = notes.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        Map<Integer, Long> countByNote = notes.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        Map<String, Object> result = new HashMap<>();
        result.put("totalAvis", total);
        result.put("averageNote", average);
        result.put("countByNote", countByNote);

        return result;
    }
}

package org.esprit.menumicroservice.Services;

import org.esprit.menumicroservice.Entities.Menu;
import org.esprit.menumicroservice.Entities.Plat;
import org.esprit.menumicroservice.Entities.PlatSpecification;
import org.esprit.menumicroservice.Repo.MenuRepository;
import org.esprit.menumicroservice.Repo.PlatRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PlatService {
    private final PlatRepository platRepository;
    private final MenuRepository menuRepository; // Inject MenuRepository to fetch the Menu by id


    public PlatService(PlatRepository platRepository, MenuRepository menuRepository) {
        this.platRepository = platRepository;
        this.menuRepository = menuRepository;
    }

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Plat createPlat(Plat plat) {
        return platRepository.save(plat);
    }

    public Plat getPlatById(Long id) {
        return platRepository.findById(id).orElse(null);
    }

    public void deletePlat(Long id) {
        platRepository.deleteById(id);
    }

    public Plat updatePlat(Long id, Plat platDetails) {
        Plat plat = platRepository.findById(id).orElse(null);
        if (plat != null) {
            // Update basic fields
            plat.setNom(platDetails.getNom());
            plat.setPrix(platDetails.getPrix());
            plat.setDescription(platDetails.getDescription());

            // Check if a menu ID was passed and update the menu
            if (platDetails.getMenu() != null && platDetails.getMenu().getIdMenu() != null) {
                Menu menu = menuRepository.findById(platDetails.getMenu().getIdMenu()).orElse(null);
                if (menu != null) {
                    plat.setMenu(menu); // Set the menu
                } else {
                    // Handle the case where the menu ID is not found
                    return null; // or throw an exception
                }
            }

            return platRepository.save(plat); // Save and return the updated plat
        }
        return null; // Return null if plat not found
    }

    // Recherche des plats avec critères par nom ou prix
    public List<Plat> searchPlats(String nom, Double prixMax) {
        Specification<Plat> specification = Specification.where(PlatSpecification.hasNom(nom))
                .and(PlatSpecification.hasPrixMax(prixMax));

        return platRepository.findAll(specification);
    }

    public Plat getPlatMystereAvecReduction() {
        List<Plat> plats = platRepository.findAll();
        if (plats.isEmpty()) {
            return null;
        }

        // Choisir un plat aléatoire
        Plat platChoisi = plats.get((int) (Math.random() * plats.size()));

        // Appliquer une réduction de 30% sur le prix
        Plat platAvecReduction = new Plat();
        platAvecReduction.setIdPlat(platChoisi.getIdPlat());
        platAvecReduction.setNom(platChoisi.getNom());
        platAvecReduction.setDescription(platChoisi.getDescription());
        platAvecReduction.setMenu(platChoisi.getMenu());

        // ✅ Réduction de 30%
        Double prixReduit = platChoisi.getPrix() * 0.70;
        platAvecReduction.setPrix(Math.round(prixReduit * 100.0) / 100.0);

        return platAvecReduction;
    }


}

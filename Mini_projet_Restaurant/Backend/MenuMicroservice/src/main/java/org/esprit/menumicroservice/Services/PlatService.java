package org.esprit.menumicroservice.Services;

import org.esprit.menumicroservice.Entities.Menu;
import org.esprit.menumicroservice.Entities.Plat;
import org.esprit.menumicroservice.Repo.MenuRepository;
import org.esprit.menumicroservice.Repo.PlatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

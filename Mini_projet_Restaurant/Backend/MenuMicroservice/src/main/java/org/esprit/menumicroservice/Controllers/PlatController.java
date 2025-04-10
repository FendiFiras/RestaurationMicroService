package org.esprit.menumicroservice.Controllers;

import org.esprit.menumicroservice.Entities.Plat;
import org.esprit.menumicroservice.Services.PlatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Menu/plats")
public class PlatController {
    private final PlatService platService;

    public PlatController(PlatService platService) {
        this.platService = platService;
    }


    // Recherche avancée de plats (par nom ou prix)
    @GetMapping("/search")
    public List<Plat> searchPlats(@RequestParam(required = false) String nom,
                                  @RequestParam(required = false) Double prixMax) {
        return platService.searchPlats(nom, prixMax);
    }

    @GetMapping("GetAllPlats")
    public List<Plat> getAllPlats() {
        return platService.getAllPlats();
    }

    @PostMapping("AddNewPlat")
    public Plat createPlat(@RequestBody Plat plat) {
        return platService.createPlat(plat);
    }

    @GetMapping("GetPlatBy/{id}")
    public Plat getPlatById(@PathVariable Long id) {
        return platService.getPlatById(id);
    }

    @DeleteMapping("DeletePlatBy/{id}")
    public void deletePlat(@PathVariable Long id) {
        platService.deletePlat(id);
    }

    // New endpoint to update a plat
    @PutMapping("UpdatePlat/{id}")
    public Plat updatePlat(@PathVariable Long id, @RequestBody Plat platDetails) {
        return platService.updatePlat(id, platDetails);
    }
}

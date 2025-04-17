package org.esprit.commandemicroservice.Services;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component // ✅ Ajoute cette ligne pour que Spring détecte le bean

public class PlatServiceFake {

    private static final Map<Long, String> fakePlats = new HashMap<>();
    private static final Map<Long, Double> fakePrixPlats = new HashMap<>();

    static {
        fakePlats.put(1L, "Pizza Margherita");
        fakePlats.put(2L, "Tacos Viande Hachée");
        fakePlats.put(3L, "Couscous Royal");
        fakePlats.put(4L, "Burger Poulet");
        fakePlats.put(5L, "Pasta Sauce Blanche");

        fakePrixPlats.put(1L, 20.0);
        fakePrixPlats.put(2L, 18.5);
        fakePrixPlats.put(3L, 25.0);
        fakePrixPlats.put(4L, 19.0);
        fakePrixPlats.put(5L, 22.0);
    }

    public String getNomPlat(Long idPlat) {
        return fakePlats.getOrDefault(idPlat, "Plat inconnu");
    }

    public Double getPrixPlat(Long idPlat) {
        return fakePrixPlats.getOrDefault(idPlat, 0.0);
    }
}
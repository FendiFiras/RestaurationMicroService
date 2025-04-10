package org.esprit.commandemicroservice.Services;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlatServiceFake {

    private static final Map<Long, String> fakePlats = new HashMap<>();

    static {
        fakePlats.put(1L, "Pizza Margherita");
        fakePlats.put(2L, "Tacos Viande Hachée");
        fakePlats.put(3L, "Couscous Royal");
        fakePlats.put(4L, "Burger Poulet");
    }

    public String getNomPlat(Long idPlat) {
        return fakePlats.getOrDefault(idPlat, "Plat inconnu");
    }
}
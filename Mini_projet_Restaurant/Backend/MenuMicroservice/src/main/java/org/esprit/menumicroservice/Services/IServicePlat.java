package org.esprit.menumicroservice.Services;


import org.esprit.menumicroservice.Entities.Plat;

import java.util.List;

public interface IServicePlat {
    List<Plat> getAllPlats();
    Plat createPlat(Plat plat);
    Plat getPlatById(Long id);
    void deletePlat(Long id);
}

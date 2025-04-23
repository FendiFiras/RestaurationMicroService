package org.esprit.commandemicroservice.clients;

import org.esprit.commandemicroservice.Dto.Livraison;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LivraisonMicroService",url = "http://localhost:8083") // pas besoin de `url` si Eureka est activé
public interface LivClient {

    @GetMapping("/livraisons/{id}")
    Livraison getLivraisonById(@PathVariable("id") Long idLivraison);
}

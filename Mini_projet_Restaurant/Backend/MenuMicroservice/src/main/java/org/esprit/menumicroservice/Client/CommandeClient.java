package org.esprit.menumicroservice.Client;

import org.esprit.menumicroservice.Dto.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CommandeMicroService",url = "http://localhost:8081")
public interface CommandeClient {

    @GetMapping("/commande/GetoneByID/{id}")
    Commande getCommandeById(@PathVariable("id") Long id);
}

package org.esprit.menumicroservice.Client;

import org.esprit.menumicroservice.Dto.Commande; // Crée ce DTO pour refléter l'entité Commande
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "CommandeMicroService") // si Eureka est activé
public interface CommandeClient {



    @GetMapping("/commande/Gett_All_Commandes")
    List<Commande> getAllCommandes();


}

package org.esprit.commandemicroservice.Controllers;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.esprit.commandemicroservice.Entites.Commande;
import org.esprit.commandemicroservice.Entites.ModePaiement;
import org.esprit.commandemicroservice.Services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/commande")
@RequiredArgsConstructor
@RefreshScope

public class CommandeController {
    @Autowired



      ICommandeService commandeService;

    @PostMapping("addCommande")
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }

    @GetMapping("Gett_All_Commandes")
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("GetoneByID/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }

    @DeleteMapping("Supprimer/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }

    @PostMapping("/{id}/generate-pdf")
    public ResponseEntity<Resource> generatePdf(@PathVariable Long id) {
        try {
            String filePath = commandeService.generateCommandePdf(id);
            File file = new File(filePath);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName()) // 📥 forcer le téléchargement
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(file.length())
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

    }

    @PutMapping("Update/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande newCommande) {
        Commande updated = commandeService.updateCommande(id, newCommande);
        return ResponseEntity.ok(updated);
    }


    @GetMapping("/sorted")
    public ResponseEntity<List<Commande>> getCommandesSorted(@RequestParam String order) {
        try {
            List<Commande> commandes = commandeService.getCommandesSortedByTotal(order);
            return ResponseEntity.ok(commandes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/filter-by-mode")
    public List<Commande> getCommandesByModePaiement(@RequestParam ModePaiement modePaiement) {
        return commandeService.findByModePaiement(modePaiement);
    }


    @PostMapping("/partagee/save")
    public ResponseEntity<List<Commande>> sauvegarderCommandePartagee(@RequestBody Map<String, Object> body) {
        List<Map<String, Object>> participants = (List<Map<String, Object>>) body.get("participants");
        List<Commande> commandes = commandeService.saveCommandePartagee(participants);
        return ResponseEntity.ok(commandes);
    }

    @Value("${welcome.message:Default welcome message}")
    private String welcomeMessage;
    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }

}

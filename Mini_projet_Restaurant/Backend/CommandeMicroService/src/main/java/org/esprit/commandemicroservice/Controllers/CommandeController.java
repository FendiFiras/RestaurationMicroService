package org.esprit.commandemicroservice.Controllers;

import lombok.RequiredArgsConstructor;
import org.esprit.commandemicroservice.Entites.Commande;
import org.esprit.commandemicroservice.Services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;

@RestController
@RequestMapping("/commande")
@RequiredArgsConstructor
public class CommandeController {
    @Autowired

      ICommandeService commandeService;

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }

    @DeleteMapping("/{id}")
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

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande newCommande) {
        Commande updated = commandeService.updateCommande(id, newCommande);
        return ResponseEntity.ok(updated);
    }




}

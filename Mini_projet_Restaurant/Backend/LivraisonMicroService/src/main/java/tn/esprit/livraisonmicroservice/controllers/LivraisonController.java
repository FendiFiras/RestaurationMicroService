package tn.esprit.livraisonmicroservice.controllers;

import tn.esprit.livraisonmicroservice.entities.Livraison;
import tn.esprit.livraisonmicroservice.services.ILivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livraisons")
@CrossOrigin("*")
public class LivraisonController {

    @Autowired
    private ILivraisonService livraisonService;

    @GetMapping
    public List<Livraison> getAll() {
        return livraisonService.getAllLivraisons();
    }

    @GetMapping("/{id}")
    public Livraison getById(@PathVariable Long id) {
        return livraisonService.getLivraisonById(id);
    }

    @PostMapping
    public Livraison create(@RequestBody Livraison livraison) {
        return livraisonService.addLivraison(livraison);
    }

    @PutMapping("/{id}")
    public Livraison update(@PathVariable Long id, @RequestBody Livraison livraison) {
        return livraisonService.updateLivraison(id, livraison);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        livraisonService.deleteLivraison(id);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Incident service is running";
    }


    @PutMapping("/update-location/{id}")
    public ResponseEntity<Livraison> updateLocation(
            @PathVariable Long id,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        Livraison livraison = livraisonService.getLivraisonById(id);
        livraison.setLatitude(latitude);
        livraison.setLongitude(longitude);
        Livraison updated = livraisonService.updateLivraison(id, livraison);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/map-link/{id}")
    public ResponseEntity<String> getMapLink(@PathVariable Long id) {
        Livraison livraison = livraisonService.getLivraisonById(id);
        if (livraison.getLatitude() != null && livraison.getLongitude() != null) {
            String url = "https://www.google.com/maps?q=" + livraison.getLatitude() + "," + livraison.getLongitude();
            return ResponseEntity.ok(url);
        }
        return ResponseEntity.badRequest().body("Location not available");
    }

    @GetMapping("/current-location")
    public ResponseEntity<String> getCurrentLocation() {
        try {
            java.net.URL url = new java.net.URL("http://ip-api.com/json/");
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return ResponseEntity.ok(content.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Unable to get location: " + e.getMessage());
        }
    }

}
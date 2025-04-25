package org.esprit.avismicroservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.esprit.avismicroservice.Entity.Avis;
import org.esprit.avismicroservice.Service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/avis")
@RequiredArgsConstructor
@RefreshScope
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping
    public List<Avis> getAllAvis() {
        return avisService.getAllAvis();
    }

    @GetMapping("/getbyid/{id}")
    public Optional<Avis> getAvisById(@PathVariable Long id) {
        return avisService.getAvisById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> ajouterAvis(@RequestBody Avis avis) {
        try {
            Avis savedAvis = avisService.createAvis(avis);
            return ResponseEntity.ok(savedAvis);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/statistics")
    public Map<String, Object> getAvisStatistics() {
        return avisService.getAvisStatistics();
    }
    @PutMapping("/update/{id}")
    public Avis updateAvis(@PathVariable Long id, @RequestBody Avis avis) {
        return avisService.updateAvis(id, avis);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAvis(@PathVariable Long id) {
        return avisService.deleteAvis(id) ? "Deleted successfully." : "Avis not found.";
    }

    @Operation(summary = "Search reviews by keyword and rating")
    @ApiResponse(responseCode = "200", description = "Found reviews")
    @GetMapping("/SmartKeywordSearch")
    public List<Avis> searchAvis(@RequestParam String keyword, @RequestParam(required = false) Integer rating) {
        return avisService.searchReviews(keyword, rating);
    }
}

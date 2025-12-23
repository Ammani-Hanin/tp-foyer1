package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.FoyerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FoyerController {

    private final FoyerService foyerService;

    @Autowired
    public FoyerController(FoyerService foyerService) {
        this.foyerService = foyerService;
    }

    @GetMapping("/foyers")
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyer();
    }

    @GetMapping("/foyers/{id}")
    public ResponseEntity<Foyer> getFoyerById(@PathVariable Long id) {
        Optional<Foyer> foyerOpt = foyerService.findById(id);
        return foyerOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/foyers")
    public ResponseEntity<Foyer> addFoyer(@RequestBody Foyer foyer) {
        foyerService.addFoyer(foyer);
        return ResponseEntity.ok(foyer);
    }

    @PutMapping("/foyers/{id}")
    public ResponseEntity<Foyer> updateFoyer(@PathVariable Long id, @RequestBody Foyer foyer) {
        Optional<Foyer> existing = foyerService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        foyerService.updateFoyer(foyer);
        return ResponseEntity.ok(foyer);
    }

    @DeleteMapping("/foyers/{id}")
    public ResponseEntity<Void> deleteFoyer(@PathVariable Long id) {
        Optional<Foyer> existing = foyerService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        foyerService.deleteFoyer(existing.get());
        return ResponseEntity.noContent().build();
    }
}

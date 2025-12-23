package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.mapper.Universite;
import tn.esprit.tpfoyer.service.UniversiteService;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
@Tag(name = "Universite", description = "CRUD operations for Universite")
public class UniversiteController {

    private final UniversiteService universiteService;

    @Autowired
    public UniversiteController(UniversiteService universiteService) {
        this.universiteService = universiteService;
    }

    @GetMapping("/universites")
    @Operation(summary = "List universites", description = "Get all universites")
    public List<Universite> getAllUniversites() {
        return universiteService.getAllUniversite();
    }

    @GetMapping("/universites/{id}")
    @Operation(summary = "Get universite by id")
    public ResponseEntity<Universite> getUniversiteById(@PathVariable Long id) {
        Optional<Universite> universiteOpt = universiteService.findById(id);
        return universiteOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/universites")
    @Operation(summary = "Create universite")
    public ResponseEntity<Universite> addUniversite(@RequestBody Universite universite) {
        universiteService.addUniversite(universite);
        return ResponseEntity.ok(universite);
    }

    @PutMapping("/universites/{id}")
    @Operation(summary = "Update universite")
    public ResponseEntity<Universite> updateUniversite(@PathVariable Long id, @RequestBody Universite universite) {
        Optional<Universite> existing = universiteService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        universiteService.updateUniversite(universite);
        return ResponseEntity.ok(universite);
    }

    @DeleteMapping("/universites/{id}")
    @Operation(summary = "Delete universite")
    public ResponseEntity<Void> deleteUniversite(@PathVariable Long id) {
        Optional<Universite> existing = universiteService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        universiteService.deleteUniversite(existing.get());
        return ResponseEntity.noContent().build();
    }
}

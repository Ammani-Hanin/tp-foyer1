package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.EtudiantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping("/etudiants")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiant();
    }

    @GetMapping("/etudiants/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(id);
        return etudiantOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/etudiants")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        etudiantService.addEtudiant(etudiant);
        return ResponseEntity.ok(etudiant);
    }

    @PutMapping("/etudiants/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        Optional<Etudiant> existing = etudiantService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        etudiantService.updateEtudiant(etudiant);
        return ResponseEntity.ok(etudiant);
    }

    @DeleteMapping("/etudiants/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        Optional<Etudiant> existing = etudiantService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        etudiantService.deleteEtudiant(existing.get());
        return ResponseEntity.noContent().build();
    }
}

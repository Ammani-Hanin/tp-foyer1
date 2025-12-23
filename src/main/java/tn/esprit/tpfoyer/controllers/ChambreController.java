package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.ChamberDTO;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.service.ChambreService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChambreController {

    private final ChambreService chambreService;

    @Autowired
    public ChambreController(ChambreService chambreService) {
        this.chambreService = chambreService;
    }

    @GetMapping("/chambres")
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambre();
    }

    @GetMapping("/chambres/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        Optional<Chambre> chambreOpt = chambreService.findById(id);
        return chambreOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/chambres")
    public ResponseEntity<Chambre> addChambre(@RequestBody Chambre chambre) {
        chambreService.addChambre(chambre);
        return ResponseEntity.ok(chambre);
    }

    @PutMapping("/chambres/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long id, @RequestBody Chambre chambre) {
        Optional<Chambre> existing = chambreService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        chambreService.updateChambre(chambre);
        return ResponseEntity.ok(chambre);
    }

    @DeleteMapping("/chambres/{id}")
    public ResponseEntity<Void> deleteChambre(@PathVariable Long id) {
        Optional<Chambre> existing = chambreService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        chambreService.deleteChambre(existing.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/chambres/{chambreId}/bloc/{blocId}")
    @Operation(summary = "Affecter un bloc à une chambre")
    public ResponseEntity<ChamberDTO> affecterBlocAChambre(
            @PathVariable Long chambreId,
            @PathVariable Integer blocId) {
        try {
            ChamberDTO chambre = chambreService.affecterBlocAChambre(chambreId, blocId);
            return ResponseEntity.ok(chambre);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/chambres/{chambreId}/reservations/{reservationId}")
    @Operation(summary = "Affecter une chambre à une réservation")
    public ResponseEntity<Chambre> affecterChambreAReservation(
            @PathVariable Long chambreId,
            @PathVariable String reservationId) {
        try {
            Chambre chambre = chambreService.affecterChambreAReservation(chambreId, reservationId);
            return ResponseEntity.ok(chambre);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

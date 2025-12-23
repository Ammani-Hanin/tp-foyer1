package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.ChambreService;
import tn.esprit.tpfoyer.service.ReservationService;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
@Tag(name = "Reservation", description = "CRUD operations for Reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ChambreService chambreService;

    @Autowired
    public ReservationController(ReservationService reservationService, ChambreService chambreService) {
        this.reservationService = reservationService;
        this.chambreService = chambreService;
    }

    @GetMapping("/reservations")
    @Operation(summary = "List reservations", description = "Get all reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservation();
    }

    @GetMapping("/reservations/{id}")
    @Operation(summary = "Get reservation by id")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        Optional<Reservation> reservationOpt = reservationService.findById(id);
        return reservationOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/reservations")
    @Operation(summary = "Create reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/reservations/{id}")
    @Operation(summary = "Update reservation")
    public ResponseEntity<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        Optional<Reservation> existing = reservationService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservationService.updateReservation(reservation);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    @Operation(summary = "Delete reservation")
    public ResponseEntity<Void> deleteReservation(@PathVariable String id) {
        Optional<Reservation> existing = reservationService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservationService.deleteReservation(existing.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reservations/{reservationId}/chambres/{chambreId}")
    @Operation(summary = "Affecter une chambre à une réservation")
    public ResponseEntity<Chambre> affecterChambreAReservation(
            @PathVariable String reservationId,
            @PathVariable Long chambreId) {
        try {
            Chambre chambre = chambreService.affecterChambreAReservation(chambreId, reservationId);
            return ResponseEntity.ok(chambre);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

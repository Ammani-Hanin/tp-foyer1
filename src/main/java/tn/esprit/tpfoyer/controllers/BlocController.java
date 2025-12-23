package tn.esprit.tpfoyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.dto.ChamberDTO;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.BlocService;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import tn.esprit.tpfoyer.service.ChambreService;

@RestController
@RequestMapping("/api")
@Tag(name = "Bloc", description = "CRUD operations for Bloc")
public class BlocController {

    private final BlocService blocService;
    private final ChambreService chambreService;

    @Autowired
    public BlocController(BlocService blocService, ChambreService chambreService) {
        this.blocService = blocService;
        this.chambreService = chambreService;
    }

    @GetMapping("/blocs")
    @Operation(summary = "List blocs", description = "Get all blocs")
    public List<Bloc> getAllBlocs() {
        return blocService.getAllBloc();
    }

    @GetMapping("/blocs/{id}")
    @Operation(summary = "Get bloc by id")
    public BlocDTO getBlocById(@PathVariable int id) {
        BlocDTO blocOpt = blocService.findById(id);
        return blocOpt ;
    }

    @PostMapping("/blocs")
    @Operation(summary = "Create bloc")
    public ResponseEntity<Bloc> addBloc(@RequestBody Bloc bloc) {
        blocService.addBloc(bloc);
        return ResponseEntity.ok(bloc);
    }

//    @PutMapping("/blocs/{id}")
//    @Operation(summary = "Update bloc")
//    public ResponseEntity<Bloc> updateBloc(@PathVariable int id, @RequestBody Bloc bloc) {
//        BlocDTO existing = blocService.findById(id);
//        if (existing.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        blocService.updateBloc(bloc);
//        return ResponseEntity.ok(bloc);
//    }

//    @DeleteMapping("/blocs/{id}")
//    @Operation(summary = "Delete bloc")
//    public ResponseEntity<Void> deleteBloc(@PathVariable int id) {
//        Optional<Bloc> existing = blocService.findById(id);
//        if (existing.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        blocService.deleteBloc(existing.get());
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/blocs/{blocId}/chambres/{chambreId}")
    @Operation(summary = "Affecter un bloc à une chambre")
    public ResponseEntity<ChamberDTO> affecterBlocAChambre(
            @PathVariable Integer blocId,
            @PathVariable Long chambreId) {
        try {
            ChamberDTO chambre = chambreService.affecterBlocAChambre(chambreId, blocId);
            return ResponseEntity.ok(chambre);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Ajouter un bloc avec ses chambres")
    public ResponseEntity<Bloc> ajouterBloc(@RequestBody Bloc bloc) {
        try {
            Bloc savedBloc = blocService.ajouterBlocEtChambres(bloc);
            return ResponseEntity.ok(savedBloc);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
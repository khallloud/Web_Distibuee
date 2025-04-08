package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.ResourceNotFoundException;
import tn.esprit.examen.nomPrenomClasseExamen.entities.DemandeAssurance;
import tn.esprit.examen.nomPrenomClasseExamen.services.IDemandeAssurance;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/Insurance")
@org.springframework.web.bind.annotation.RestController
public class DemandeAssuranceController {
    private final IDemandeAssurance services;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public DemandeAssurance add(@RequestBody DemandeAssurance demandeAssurance){
        return  services.add(demandeAssurance);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<DemandeAssurance> getAll() {
        return services.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public DemandeAssurance getById(@PathVariable Long id) {
        return services.getById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{id}")
    public ResponseEntity<DemandeAssurance> updateDemande(@PathVariable Long id, @RequestBody DemandeAssurance updatedInsurance) {
        try {
            DemandeAssurance updatedDemande = services.update(id, updatedInsurance);
            return ResponseEntity.ok(updatedDemande);  // Retourner la demande mise à jour avec un code HTTP 200
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Demande non trouvée
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Erreur serveur
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!services.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur : La demande avec l'ID " + id + " n'existe pas !");
        }

        services.delete(id);
        return ResponseEntity.ok("Demande supprimée avec succès !");
    }


}

package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.ResourceNotFoundException;
import tn.esprit.examen.nomPrenomClasseExamen.entities.DemandeAssurance;
import tn.esprit.examen.nomPrenomClasseExamen.entities.TypeAssurance;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.InsuranceParticularRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.TypeAssuranceRepository;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceDemandeAssurance implements IDemandeAssurance {

    private final InsuranceParticularRepository demandeAssuranceRepository;
    private final TypeAssuranceRepository typeAssuranceRepository; // Ajout pour vérifier l'existence du type d'assurance

    @Override
    public DemandeAssurance add(DemandeAssurance demandeAssurance) {
        // Vérifier si le type d'assurance existe avant de l'affecter
        TypeAssurance typeAssurance = typeAssuranceRepository.findById(demandeAssurance.getTypeAssurance().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Type d'assurance non trouvé avec l'ID : "
                        + demandeAssurance.getTypeAssurance().getId()));

        demandeAssurance.setTypeAssurance(typeAssurance); // Associe un type existant

        return demandeAssuranceRepository.save(demandeAssurance);
    }

    @Override
    public List<DemandeAssurance> getAll() {
        return demandeAssuranceRepository.findAll();
    }

    @Override
    public DemandeAssurance getById(Long id) {
        return demandeAssuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande d'assurance non trouvée avec l'ID : " + id));
    }

    @Override
    public DemandeAssurance update(Long id, DemandeAssurance updatedInsurance) {
        return demandeAssuranceRepository.findById(id).map(demande -> {
            demande.setNomAssurance(updatedInsurance.getNomAssurance());
            demande.setDescription(updatedInsurance.getDescription());
            demande.setPrix(updatedInsurance.getPrix());
            demande.setTypeAssurance(updatedInsurance.getTypeAssurance());
            // Vérification du type d'assurance
            TypeAssurance typeAssurance = typeAssuranceRepository.findById(updatedInsurance.getTypeAssurance().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("TypeAssurance non trouvé"));

            demande.setTypeAssurance(typeAssurance);

            return demandeAssuranceRepository.save(demande);
        }).orElseThrow(() -> new ResourceNotFoundException("Demande d'assurance avec ID " + id + " non trouvée"));
    }

    @Override
    public void delete(Long id) {
        if (!demandeAssuranceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Demande d'assurance avec ID " + id + " non trouvée");
        }
        demandeAssuranceRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return demandeAssuranceRepository.existsById(id);
    }

    public Map<String, Long> getTypeAssuranceStatistics() {
        return typeAssuranceRepository.getTypeAssuranceStatistics();
    }

}

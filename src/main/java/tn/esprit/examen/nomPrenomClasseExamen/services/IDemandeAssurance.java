package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.entities.DemandeAssurance;

import java.util.List;

public interface IDemandeAssurance {
    DemandeAssurance add(DemandeAssurance demandeAssurance);
    List<DemandeAssurance> getAll();
    DemandeAssurance getById(Long id);
    DemandeAssurance update(Long id, DemandeAssurance updatedInsurance);
    void delete(Long id);


    boolean existsById(Long id);


}

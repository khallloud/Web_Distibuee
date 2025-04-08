package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class DemandeAssurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NomAssurance; // Nom et prénom du demandeur
    private String Description; // CIN ou Passeport
    private Long Prix;

    @ManyToOne(cascade = CascadeType.ALL)
    private TypeAssurance typeAssurance;
}

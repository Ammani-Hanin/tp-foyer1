package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEtudiant;

    String nomEt;
    String prenomEt;
    long cin;
    String ecole;

    @Temporal(TemporalType.DATE)
    Date dateNaissance;

    @ManyToMany(mappedBy = "etudiants")
    List<Reservation> reservations;
}
package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import tn.esprit.tpfoyer.mapper.Universite;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Foyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFoyer;

    String nomFoyer;
    long capaciteFoyer;

    @OneToOne(mappedBy = "foyer")
    Universite universite;

    @OneToMany(mappedBy = "foyer")
    List<Bloc> blocs;
}
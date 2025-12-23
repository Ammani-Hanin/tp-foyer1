package tn.esprit.tpfoyer.mapper;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import tn.esprit.tpfoyer.entity.Foyer;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Universite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUniversite;

    String nomUniversite;
    String adresse;


    @OneToOne
    Foyer foyer;
}
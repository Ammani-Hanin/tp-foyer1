package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tn.esprit.tpfoyer.entity.Bloc; // OK

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Bloc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idBloc;

    String nomBloc;
    long capaciteBloc;

    @ManyToOne
    @JsonIgnoreProperties("blocs")
    Foyer foyer;

    @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Chambre> chambres;

}
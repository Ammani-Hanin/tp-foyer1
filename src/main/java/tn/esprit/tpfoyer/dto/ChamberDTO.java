package tn.esprit.tpfoyer.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChamberDTO {
    long    id;
    long    num;
    BlocDTO bloc;

    public static ChamberDTO mapper(Chambre chambre){
       return ChamberDTO.builder().id(chambre.getIdChambre()).num(chambre.getNumeroChambre()).bloc(BlocDTO.mapper(chambre.getBloc())).build();
    }
}

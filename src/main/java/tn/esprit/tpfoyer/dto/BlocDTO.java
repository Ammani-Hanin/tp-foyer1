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
public class BlocDTO {
    long id;
    String nomBloc;
    long capaciteBloc;
    public static BlocDTO mapper(Bloc bloc){
        return BlocDTO.builder().id(bloc.getIdBloc()).capaciteBloc(bloc.getCapaciteBloc()).nomBloc(bloc.getNomBloc()).build();

    }
}

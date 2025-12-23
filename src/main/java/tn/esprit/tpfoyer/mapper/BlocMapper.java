package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.entity.Bloc;

@Mapper(componentModel = "spring")
public interface BlocMapper {

    @Mapping(target = "id", source = "idBloc")
    BlocDTO toDto(Bloc bloc);

    @Mapping(target = "idBloc", source = "id")
    Bloc toEntity(BlocDTO blocDto);
}


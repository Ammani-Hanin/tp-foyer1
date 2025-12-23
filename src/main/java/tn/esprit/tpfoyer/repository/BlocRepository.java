package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Integer> {

    List<Bloc> findByFoyerCapaciteFoyer(Long capaciteFoyer);

    List<Bloc> findByChambresTypeC(TypeChambre typeC);

    List<Bloc> findByCapaciteBlocLessThanOrCapaciteBlocGreaterThan(int min, int max);

    @Query("select b from Bloc b join b.chambres c where c.idChambre = :idChambre")

    BlocDTO getBlocByChambreId(@Param("idChambre") Long idChambre);

}

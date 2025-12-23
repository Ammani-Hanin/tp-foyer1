package tn.esprit.tpfoyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.entity.Bloc; // OK
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlocService {

    @Autowired
    BlocRepository blocRepository;

    @Autowired
    ChambreRepository chambreRepository;

    public void addBloc(Bloc bloc) {
        blocRepository.save(bloc);
    }
    public void updateBloc(Bloc bloc) {
        blocRepository.save(bloc);
    }
    public List<Bloc> getAllBloc() {
        return blocRepository.findAll();
    }
    public BlocDTO findById(int id) {
        return blocRepository.getBlocByChambreId((long) id);
    }
    public void deleteBloc(Bloc bloc) {
        blocRepository.delete(bloc);
    }

    public List<Bloc> getBlocByCapaciteFoyer(Long capaciteFoyer) {
            return blocRepository.findByFoyerCapaciteFoyer(capaciteFoyer);
    }

    public List<Bloc> getBlocByTypeC(TypeChambre typeC) {
        return blocRepository.findByChambresTypeC(typeC);
    }

    public List<Bloc> getBlocsByCapaciteBetween(int min, int max) {
        return blocRepository.findByCapaciteBlocLessThanOrCapaciteBlocGreaterThan(min, max);
    }

    public Bloc ajouterBlocEtChambres(Bloc bloc) {
        if (bloc.getChambres() != null && !bloc.getChambres().isEmpty()) {
            for (Chambre c : bloc.getChambres()) {
                c.setBloc(bloc);
            }
        }
        return blocRepository.save(bloc);
    }

}

package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.dto.ChamberDTO;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.mapper.BlocMapper;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ChambreService {

    private final BlocMapper blocMapper;

    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    BlocRepository blocRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public BlocDTO addBloc(Bloc bloc) {
        return blocMapper.toDto(blocRepository.save(bloc));
    }

    public void addChambre(Chambre chambre) {
        chambreRepository.save(chambre);
    }

    public void updateChambre(Chambre chambre) {
        chambreRepository.save(chambre);
    }

    public List<Chambre> getAllChambre() {
        return chambreRepository.findAll();
    }

    public Optional<Chambre> findById(Long id) {
        return chambreRepository.findById(id);
    }

    public void deleteChambre(Chambre chambre) {
        chambreRepository.delete(chambre);
    }

    public ChamberDTO affecterBlocAChambre(Long chambreId, Integer blocId) {

        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre introuvable"));

        Bloc bloc = blocRepository.findById(blocId)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable"));

        chambre.setBloc(bloc);
       return ChamberDTO.mapper(chambreRepository.save(chambre));
    }

    public Chambre affecterChambreAReservation(Long chambreId, String reservationId) {
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre introuvable"));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation introuvable"));



        if (!chambre.getReservations().contains(reservation)) {
            chambre.getReservations().add(reservation);
        }

        return chambreRepository.save(chambre);
    }

}

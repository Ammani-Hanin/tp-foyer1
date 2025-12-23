package tn.esprit.tpfoyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ChambreRepository chambreRepository;

    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(String id) {
        return reservationRepository.findById(id);
    }

    public void deleteReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

}

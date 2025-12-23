package tn.esprit.tpfoyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FoyerService {

    @Autowired
    FoyerRepository foyerRepository;

    public void addFoyer(Foyer foyer) {
        foyerRepository.save(foyer);
    }

    public void updateFoyer(Foyer foyer) {
        foyerRepository.save(foyer);
    }

    public List<Foyer> getAllFoyer() {
        return foyerRepository.findAll();
    }

    public Optional<Foyer> findById(Long id) {
        return foyerRepository.findById(id);
    }

    public void deleteFoyer(Foyer foyer) {
        foyerRepository.delete(foyer);
    }
}

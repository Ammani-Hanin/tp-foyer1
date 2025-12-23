package tn.esprit.tpfoyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.mapper.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UniversiteService {

    @Autowired
    UniversiteRepository universiteRepository;

    public void addUniversite(Universite universite) {
        universiteRepository.save(universite);
    }

    public void updateUniversite(Universite universite) {
        universiteRepository.save(universite);
    }

    public List<Universite> getAllUniversite() {
        return universiteRepository.findAll();
    }

    public Optional<Universite> findById(Long id) {
        return universiteRepository.findById(id);
    }

    public void deleteUniversite(Universite universite) {
        universiteRepository.delete(universite);
    }
}

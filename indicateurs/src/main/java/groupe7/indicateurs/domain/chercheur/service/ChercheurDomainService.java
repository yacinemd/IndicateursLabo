package groupe7.indicateurs.domain.chercheur.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.chercheur.model.Chercheur;
import groupe7.indicateurs.domain.chercheur.repository.ChercheurRepository;

@Service
public class ChercheurDomainService {
    private final ChercheurRepository chercheurRepository;

    public ChercheurDomainService(ChercheurRepository chercheurRepository) {
        this.chercheurRepository = chercheurRepository;
    }

    public void addChercheur(Chercheur chercheur) {
        chercheurRepository.save(chercheur);
    }

    public Optional<Chercheur> findChercheurById(Long id) {
        return chercheurRepository.findById(id);
    }

    public void removeChercheur(Long id) {
        chercheurRepository.deleteById(id);
    }
}
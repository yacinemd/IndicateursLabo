package groupe7.indicateurs.domain.autre_poste.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.autre_poste.model.AutrePoste;
import groupe7.indicateurs.domain.autre_poste.repository.AutrePosteRepository;

@Service
public class AutrePosteDomainService {
    private final AutrePosteRepository autrePosteRepository;

    public AutrePosteDomainService(AutrePosteRepository autrePosteRepository) {
        this.autrePosteRepository = autrePosteRepository;
    }

    public void addAutrePoste(AutrePoste autrePoste) {
        autrePosteRepository.save(autrePoste);
    }

    public Optional<AutrePoste> findAutrePosteById(Long id) {
        return autrePosteRepository.findById(id);
    }

    public void removeAutrePoste(Long id) {
        autrePosteRepository.deleteById(id);
    }
}

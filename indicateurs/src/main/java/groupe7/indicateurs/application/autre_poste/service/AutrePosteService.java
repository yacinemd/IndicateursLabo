package groupe7.indicateurs.application.autre_poste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.autre_poste.model.AutrePoste;
import groupe7.indicateurs.domain.autre_poste.repository.AutrePosteRepository;

@Service
public class AutrePosteService {

    private final AutrePosteRepository autrePosteRepository;

    public AutrePosteService(AutrePosteRepository autrePosteRepository) {
        this.autrePosteRepository = autrePosteRepository;
    }

    public List<AutrePoste> findAll() {
        return autrePosteRepository.findAll();
    }

    public Optional<AutrePoste> findById(Long id) {
        return autrePosteRepository.findById(id);
    }

    public AutrePoste save(AutrePoste autrePoste) {
        return autrePosteRepository.save(autrePoste);
    }

    public void deleteById(Long id) {
        autrePosteRepository.deleteById(id);
    }
    public List<AutrePoste> getAutresPostesByNomOrPrenom(String nom, String prenom) {
        return autrePosteRepository.findByNomOrPrenomAutrePoste(nom, prenom);
    }
}

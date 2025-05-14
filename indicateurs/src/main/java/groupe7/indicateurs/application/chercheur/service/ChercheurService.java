package groupe7.indicateurs.application.chercheur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.chercheur.model.Chercheur;
import groupe7.indicateurs.domain.chercheur.repository.ChercheurRepository;

@Service
public class ChercheurService {

    private final ChercheurRepository chercheurRepository;

    public ChercheurService(ChercheurRepository chercheurRepository) {
        this.chercheurRepository = chercheurRepository;
    }

    public List<Chercheur> findAll() {
        return chercheurRepository.findAll();
    }

    public Optional<Chercheur> findById(Long id) {
        return chercheurRepository.findById(id);
    }

    public Chercheur save(Chercheur chercheur) {
        return chercheurRepository.save(chercheur);
    }

    public void deleteById(Long id) {
        chercheurRepository.deleteById(id);
    }


    public List<Chercheur> getChercheursNomByNomOrPrenom(String nom, String prenom) {
    return chercheurRepository.findByNomOrPrenom(nom, prenom);
}

}
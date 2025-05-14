package groupe7.indicateurs.domain.projet.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import groupe7.indicateurs.domain.projet.model.Projet;
import groupe7.indicateurs.domain.projet.repository.ProjetRepository;;

@Service
public class ProjetDomainService {

    private final ProjetRepository projetRepository;

    public ProjetDomainService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public void addProjet(Projet projet) {
        projetRepository.save(projet);
    }

    public Optional<Projet> findProjetById(Long id) {
        return projetRepository.findById(id);
    }

    public void removeProjet(Long id) {
        projetRepository.deleteById(id);
    }


}

package groupe7.indicateurs.domain.livre.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import groupe7.indicateurs.domain.livre.model.Livre;
import groupe7.indicateurs.domain.livre.repository.LivreRepository;



@Service
public class LivreDomainService {
    private final LivreRepository livreRepository;

    public LivreDomainService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public void addLivre(Livre livre) {
        livreRepository.save(livre);
    }

    public Optional<Livre> findLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public void removeLivre(Long id) {
        livreRepository.deleteById(id);
    }
}

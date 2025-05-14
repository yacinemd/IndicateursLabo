package groupe7.indicateurs.domain.revue.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.revue.model.Revue;
import groupe7.indicateurs.domain.revue.repository.RevueRepository;

@Service
public class RevueDomainService {

    private final RevueRepository revueRepository;

    public RevueDomainService(RevueRepository revueRepository) {
        this.revueRepository = revueRepository;
    }

    public void addRevue(Revue revue) {
        revueRepository.save(revue);
    }

    public Optional<Revue> getRevueById(Long id) {
        return revueRepository.findById(id);
    }

    public void removeRevue(Long id) {
        revueRepository.deleteById(id);
    }
}

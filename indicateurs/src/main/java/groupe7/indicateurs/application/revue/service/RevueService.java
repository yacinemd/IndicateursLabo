package groupe7.indicateurs.application.revue.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.revue.model.Revue;
import groupe7.indicateurs.domain.revue.repository.RevueRepository;

@Service
public class RevueService {

    private final RevueRepository revueRepository;

    public RevueService(RevueRepository revueRepository) {
        this.revueRepository = revueRepository;
    }

    public List<Revue> findAll() {
        return revueRepository.findAll();
    }

    public Optional<Revue> findById(Long id) {
        return revueRepository.findById(id);
    }

    public Revue save(Revue revue) {
        return revueRepository.save(revue);
    }

    public void deleteById(Long id) {
        revueRepository.deleteById(id);
    }

    public Long getRevueIdByTitreAndPublisher(String titre, String publisher) {
        return revueRepository.findIdByTitreAndPublisher(titre, publisher);
    }

    public List<Revue> getRevuesByTitre(String titre){
        return revueRepository.findByTitre(titre);
    }

    public List<Revue> getRevuesByPublisher(String publisher){
        return revueRepository.findByPublisher(publisher);
    }

    public List<Revue> getRevuesByYear(Year year) {
        return revueRepository.findByYear(year);
    }

    public List<Revue> findByTitreOrPublisher(String queryparam) {
        return revueRepository.findByTitreOrPublisher(queryparam);
    }

    public List<Revue> findRevuesByEquipe(String equipe){
        return revueRepository.findRevuesByEquipe(equipe);
    }


}

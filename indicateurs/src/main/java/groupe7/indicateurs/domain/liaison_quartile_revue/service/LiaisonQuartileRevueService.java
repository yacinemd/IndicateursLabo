package groupe7.indicateurs.domain.liaison_quartile_revue.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.liaison_quartile_revue.model.LiaisonQuartileRevue;
import groupe7.indicateurs.domain.liaison_quartile_revue.repository.LiaisonQuartileRevueRepository;
import groupe7.indicateurs.domain.quartile.model.Quartile;
import groupe7.indicateurs.domain.quartile.repository.QuartileRepository;
import groupe7.indicateurs.domain.revue.model.Revue;
import groupe7.indicateurs.domain.revue.repository.RevueRepository;
import jakarta.transaction.Transactional;

@Service
public class LiaisonQuartileRevueService {

    private final LiaisonQuartileRevueRepository liaisonQuartileRevueRepository;
    private final QuartileRepository quartileRepository;
    private final RevueRepository revueRepository;

    public LiaisonQuartileRevueService(LiaisonQuartileRevueRepository liaisonQuartileRevueRepository,
                                       QuartileRepository quartileRepository, RevueRepository revueRepository) {
        this.liaisonQuartileRevueRepository = liaisonQuartileRevueRepository;
        this.quartileRepository = quartileRepository;
        this.revueRepository = revueRepository;
    }

    // Méthode pour lier un quartile à une revue
    @Transactional
    public void lierQuartileARevue(Long quartileId, Long revueId) {
        Optional<Quartile> quartileOptional = quartileRepository.findById(quartileId);
        Optional<Revue> revueOptional = revueRepository.findById(revueId);

        if (quartileOptional.isPresent() && revueOptional.isPresent()) {
            Quartile quartile = quartileOptional.get();
            Revue revue = revueOptional.get();

            // Créer la liaison entre le quartile et la revue
            LiaisonQuartileRevue liaison = new LiaisonQuartileRevue(quartile, revue);

            // Sauvegarder la liaison dans la base de données
            liaisonQuartileRevueRepository.save(liaison);
        } else {
            throw new IllegalArgumentException("Le quartile ou la revue n'existe pas");
        }
    }

    // Méthode pour supprimer une liaison
    @Transactional
    public void supprimerLiaison(Long quartileId, Long revueId) {
        LiaisonQuartileRevue liaison = liaisonQuartileRevueRepository
                .findByQuartileIdAndRevueId(quartileId, revueId);

        if (liaison != null) {
            // Supprimer la liaison
            liaisonQuartileRevueRepository.delete(liaison);
        } else {
            throw new IllegalArgumentException("Aucune liaison trouvée entre ce quartile et cette revue");
        }
    }

    // Méthode pour récupérer une liaison par son ID
    public Optional<LiaisonQuartileRevue> findLiaisonById(Long id) {
        return liaisonQuartileRevueRepository.findById(id);
    }

    // Méthode pour vérifier si une liaison existe entre un quartile et une revue
    public boolean estLiaisonExiste(Long quartileId, Long revueId) {
        return liaisonQuartileRevueRepository
                .findByQuartileIdAndRevueId(quartileId, revueId) != null;
    }
}

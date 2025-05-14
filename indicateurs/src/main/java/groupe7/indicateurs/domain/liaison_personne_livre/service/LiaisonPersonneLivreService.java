package groupe7.indicateurs.domain.liaison_personne_livre.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.liaison_personne_livre.model.LiaisonPersonneLivre;
import groupe7.indicateurs.domain.liaison_personne_livre.repository.LiaisonPersonneLivreRepository;
import groupe7.indicateurs.domain.livre.model.Livre;
import groupe7.indicateurs.domain.livre.repository.LivreRepository;
import groupe7.indicateurs.domain.personne.model.Personne;
import groupe7.indicateurs.domain.personne.repository.PersonneRepository;
import jakarta.transaction.Transactional;

@Service
public class LiaisonPersonneLivreService {

    private final LiaisonPersonneLivreRepository liaisonPersonnelivreRepository;
    private final PersonneRepository personneRepository;
    private final LivreRepository livreRepository;

    public LiaisonPersonneLivreService(LiaisonPersonneLivreRepository liaisonPersonneLivreRepository,
                                           PersonneRepository personneRepository, LivreRepository livreRepository) {
        this.liaisonPersonnelivreRepository = liaisonPersonneLivreRepository;
        this.personneRepository = personneRepository;
        this.livreRepository = livreRepository;
    }

    // Méthode pour lier une personne à un livre
    @Transactional
    public void lierPersonneALivre(Long personneId, Long livreId) {
        Optional<Personne> personneOptional = personneRepository.findById(personneId);
        Optional<Livre> livreOptional = livreRepository.findById(livreId);

        if (personneOptional.isPresent() && livreOptional.isPresent()) {
            Personne personne = personneOptional.get();
            Livre livre = livreOptional.get();

            // Créer la liaison entre la personne et livre
            LiaisonPersonneLivre liaison = new LiaisonPersonneLivre(personne, livre);

            // Sauvegarder la liaison dans la base de données
            liaisonPersonnelivreRepository.save(liaison);
        } else {
            throw new IllegalArgumentException("La personne ou la conférence n'existe pas");
        }
    }

    // Méthode pour supprimer une liaison
    @Transactional
    public void supprimerLiaison(Long personneId, Long livreId) {
        LiaisonPersonneLivre liaison = liaisonPersonnelivreRepository
                .findByPersonneIdAndLivreId(personneId, livreId);

        if (liaison != null) {
            // Supprimer la liaison
            liaisonPersonnelivreRepository.delete(liaison);
        } else {
            throw new IllegalArgumentException("Aucune liaison trouvée entre cette personne et cette conférence");
        }
    }

    // Méthode pour récupérer une liaison par son ID
    public Optional<LiaisonPersonneLivre> findLiaisonById(Long id) {
        return liaisonPersonnelivreRepository.findById(id);
    }

    // Méthode pour vérifier si une personne est liée à une conférence
    public boolean estLiaisonExiste(Long personneId, Long livreId) {
        return liaisonPersonnelivreRepository
                .findByPersonneIdAndLivreId(personneId, livreId) != null;
    }
}

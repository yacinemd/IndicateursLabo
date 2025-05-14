package groupe7.indicateurs.domain.liaison_personne_conference.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.conference.model.Conference;
import groupe7.indicateurs.domain.conference.repository.ConferenceRepository;
import groupe7.indicateurs.domain.liaison_personne_conference.model.LiaisonPersonneConference;
import groupe7.indicateurs.domain.liaison_personne_conference.repository.LiaisonPersonneConferenceRepository;
import groupe7.indicateurs.domain.personne.model.Personne;
import groupe7.indicateurs.domain.personne.repository.PersonneRepository;
import jakarta.transaction.Transactional;

@Service
public class LiaisonPersonneConferenceService {

    private final LiaisonPersonneConferenceRepository liaisonPersonneConferenceRepository;
    private final PersonneRepository personneRepository;
    private final ConferenceRepository conferenceRepository;

    public LiaisonPersonneConferenceService(LiaisonPersonneConferenceRepository liaisonPersonneConferenceRepository,
                                           PersonneRepository personneRepository, ConferenceRepository conferenceRepository) {
        this.liaisonPersonneConferenceRepository = liaisonPersonneConferenceRepository;
        this.personneRepository = personneRepository;
        this.conferenceRepository = conferenceRepository;
    }

    // Méthode pour lier une personne à une conférence
    @Transactional
    public void lierPersonneAConference(Long personneId, Long conferenceId) {
        Optional<Personne> personneOptional = personneRepository.findById(personneId);
        Optional<Conference> conferenceOptional = conferenceRepository.findById(conferenceId);

        if (personneOptional.isPresent() && conferenceOptional.isPresent()) {
            Personne personne = personneOptional.get();
            Conference conference = conferenceOptional.get();

            // Créer la liaison entre la personne et la conférence
            LiaisonPersonneConference liaison = new LiaisonPersonneConference(personne, conference);

            // Sauvegarder la liaison dans la base de données
            liaisonPersonneConferenceRepository.save(liaison);
        } else {
            throw new IllegalArgumentException("La personne ou la conférence n'existe pas");
        }
    }

    // Méthode pour supprimer une liaison
    @Transactional
    public void supprimerLiaison(Long personneId, Long conferenceId) {
        LiaisonPersonneConference liaison = liaisonPersonneConferenceRepository
                .findByPersonneIdAndConferenceId(personneId, conferenceId);

        if (liaison != null) {
            // Supprimer la liaison
            liaisonPersonneConferenceRepository.delete(liaison);
        } else {
            throw new IllegalArgumentException("Aucune liaison trouvée entre cette personne et cette conférence");
        }
    }

    // Méthode pour récupérer une liaison par son ID
    public Optional<LiaisonPersonneConference> findLiaisonById(Long id) {
        return liaisonPersonneConferenceRepository.findById(id);
    }

    // Méthode pour vérifier si une personne est liée à une conférence
    public boolean estLiaisonExiste(Long personneId, Long conferenceId) {
        return liaisonPersonneConferenceRepository
                .findByPersonneIdAndConferenceId(personneId, conferenceId) != null;
    }
}

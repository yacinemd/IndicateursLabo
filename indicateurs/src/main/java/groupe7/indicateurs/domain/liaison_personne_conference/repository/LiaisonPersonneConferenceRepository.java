package groupe7.indicateurs.domain.liaison_personne_conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.conference.model.Conference;
import groupe7.indicateurs.domain.liaison_personne_conference.model.LiaisonPersonneConference;
import groupe7.indicateurs.domain.personne.model.Personne;

@Repository
public interface LiaisonPersonneConferenceRepository extends JpaRepository<LiaisonPersonneConference, Long> {

    @Query("SELECT l FROM LiaisonPersonneConference l WHERE l.personne.id = :personneId AND l.conference.id = :conferenceId")
    LiaisonPersonneConference findByPersonneIdAndConferenceId(Long personneId, Long conferenceId);

    @Query("SELECT COUNT(l) > 0 FROM LiaisonPersonneConference l WHERE l.personne = :personne AND l.conference = :conference")
    boolean existsByPersonneAndConference(Personne personne, Conference conference);
}

package groupe7.indicateurs.domain.liaison_personne_livre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.liaison_personne_livre.model.LiaisonPersonneLivre;
import groupe7.indicateurs.domain.livre.model.Livre;
import groupe7.indicateurs.domain.personne.model.Personne;

@Repository
public interface LiaisonPersonneLivreRepository extends JpaRepository<LiaisonPersonneLivre, Long> {

    @Query("SELECT l FROM LiaisonPersonneLivre l WHERE l.personne.id = :personneId AND l.livre.id = :livreId")
    LiaisonPersonneLivre findByPersonneIdAndLivreId(Long personneId, Long livreId);

    @Query("SELECT COUNT(l) > 0 FROM LiaisonPersonneLivre l WHERE l.personne = :personne AND l.livre = :livre")
    boolean existsByPersonneAndLivre(Personne personne, Livre livre);
}

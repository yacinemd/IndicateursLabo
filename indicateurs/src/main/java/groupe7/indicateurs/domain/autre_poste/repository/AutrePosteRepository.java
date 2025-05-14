package groupe7.indicateurs.domain.autre_poste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.autre_poste.model.AutrePoste;

@Repository
public interface AutrePosteRepository extends JpaRepository<AutrePoste, Long> {
    @Query("SELECT a FROM AutrePoste a JOIN a.personnel p JOIN p.personne pe WHERE pe.nom LIKE %:nom% OR pe.prenom LIKE %:prenom%")
    List<AutrePoste> findByNomOrPrenomAutrePoste(@Param("nom") String nom, @Param("prenom") String prenom);
}

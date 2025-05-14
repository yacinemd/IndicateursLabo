package groupe7.indicateurs.domain.chercheur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.chercheur.model.Chercheur;

@Repository
public interface ChercheurRepository extends JpaRepository<Chercheur, Long>{

@Query("SELECT c FROM Chercheur c JOIN c.personnel p JOIN p.personne pe WHERE pe.nom LIKE %:nom% OR pe.prenom LIKE %:prenom%")
List<Chercheur> findByNomOrPrenom(@Param("nom") String nom, @Param("prenom") String prenom);


}

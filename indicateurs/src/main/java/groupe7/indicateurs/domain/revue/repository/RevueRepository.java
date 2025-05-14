package groupe7.indicateurs.domain.revue.repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.revue.model.Revue;

@Repository
public interface RevueRepository extends JpaRepository<Revue, Long> {
    @Query("SELECT r FROM Revue r WHERE r.titre = :titre AND r.publisher = :publisher")
    Optional<Revue> findByTitreAndPublisher(@Param("titre") String titre, @Param("publisher") String publisher);

    @Query("SELECT r.id FROM Revue r WHERE r.titre = :titre AND r.publisher = :publisher")
    Long findIdByTitreAndPublisher(@Param("titre") String titre, @Param("publisher") String publisher);


    @Query("SELECT r FROM Revue r WHERE r.titre LIKE %:titre%")
    List<Revue> findByTitre(@Param("titre") String titre);

    @Query("SELECT r FROM Revue r WHERE r.publisher LIKE %:publisher%")
    List<Revue> findByPublisher(@Param("publisher") String publisher);

    @Query("SELECT r FROM Revue r WHERE r.annee = :year")
    List<Revue> findByYear(@Param("year") Year year);

    @Query("SELECT r FROM Revue r WHERE r.titre LIKE %:queryparam% OR r.publisher LIKE %:queryparam%")
    List<Revue> findByTitreOrPublisher(@Param("queryparam") String queryparam);


    @Query(value = """
    SELECT DISTINCT r.* FROM revue r
    JOIN article a ON r.re_id_revue = a.at_id_revue
    JOIN liaison_personne_article lpa ON a.at_id_article = lpa.au_id_article
    JOIN personne p ON lpa.au_id_personne = p.pe_id_personne
    JOIN personnel pr ON p.pe_id_personne = pr.pr_id_personnel
    WHERE pr.pr_equipe = :equipe
    """, nativeQuery = true)
    List<Revue> findRevuesByEquipe(@Param("equipe") String equipe);



}

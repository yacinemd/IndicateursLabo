package groupe7.indicateurs.domain.livre.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.livre.model.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre,Long> {
    
    @Query("SELECT l FROM Livre l WHERE l.titre LIKE %:titre%")
    List<Livre> findByTitre(@Param("titre") String titre);

    @Query("SELECT l FROM Livre l WHERE l.annee BETWEEN :date1 AND :date2")
    List<Livre> findByYear(@Param("date1") Year date1, @Param("date2") Year date2);

    @Query("SELECT l.annee, COUNT(l) FROM Livre l GROUP BY l.annee ORDER BY l.annee DESC")
    List<Object[]> countLivresByYear();
    
    @Query("SELECT count(l) FROM Livre l WHERE l.annee BETWEEN :date1 AND :date2 AND l.pays LIKE %:country%")
    Integer countByYearAndCountry(@Param("date1") Year date1, @Param("date2") Year date2, @Param("country") String country);

    @Query("SELECT l FROM Livre l WHERE l.annee BETWEEN :date1 AND :date2 AND l.pays LIKE %:country%")
    List<Livre> findByYearAndCountry(@Param("date1") Year date1, @Param("date2") Year date2, @Param("country") String country);

    @Query(value = """
    SELECT DISTINCT l.*
    FROM personnel pr
    JOIN personne pe ON pr.pr_id_personnel = pe.pe_id_personne
    JOIN liaison_personne_livre pl ON pl.pl_id_personne = pe.pe_id_personne
    JOIN livre l ON l.li_id_livre = pl.pl_id_livre
    WHERE pr.pr_equipe LIKE %:equipe%
    """, nativeQuery = true)
    List<Livre> findByTeam(@Param("equipe") String equipe);

}

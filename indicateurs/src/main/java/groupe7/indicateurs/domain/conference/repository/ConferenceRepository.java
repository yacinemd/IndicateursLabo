package groupe7.indicateurs.domain.conference.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.conference.model.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    @Query("SELECT c FROM Conference c WHERE c.titre LIKE %:titre%")
    List<Conference> findByTitre(@Param("titre") String titre);



    @Query("SELECT c.annee, COUNT(c) FROM Conference c GROUP BY c.annee ORDER BY c.annee DESC")
    List<Object[]> countConferencesByYear();



    @Query("SELECT c FROM Conference c WHERE c.annee BETWEEN :date1 AND :date2")
    List<Conference> findByYear(@Param("date1") Year date1, @Param("date2") Year date2);

    @Query("SELECT count(c) FROM Conference c WHERE c.annee BETWEEN :date1 AND :date2 AND c.pays LIKE %:country%")
    Integer countByYearAndCountry(@Param("date1") Year date1, @Param("date2") Year date2, @Param("country") String country);

    @Query("SELECT c FROM Conference c WHERE c.annee BETWEEN :date1 AND :date2 AND c.pays LIKE %:country%")
    List<Conference> findByYearAndCountry(@Param("date1") Year date1, @Param("date2") Year date2, @Param("country") String country);

    @Query(value = """
    SELECT DISTINCT c.*
    FROM personnel pr
    JOIN personne pe ON pr.pr_id_personnel = pe.pe_id_personne
    JOIN liaison_personne_conference pc ON pc.pc_id_personne = pe.pe_id_personne
    JOIN conference c ON c.cf_id_conf = pc.pc_id_conference
    WHERE pr.pr_equipe LIKE %:equipe%
    """, nativeQuery = true)
    List<Conference> findConferencesByTeam(@Param("equipe") String equipe);

}

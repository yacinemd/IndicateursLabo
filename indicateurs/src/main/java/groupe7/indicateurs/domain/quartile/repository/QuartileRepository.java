package groupe7.indicateurs.domain.quartile.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.quartile.model.Quartile;

@Repository
public interface QuartileRepository extends JpaRepository<Quartile, Long> {

    @Query("SELECT q FROM Quartile q WHERE q.quartile LIKE %:quartile%")
    List<Quartile> findByQuartile(@Param("quartile") String quartile);


    @Query(value = "SELECT r.re_titre AS titre, r.re_publisher AS publisher, q.ql_domaine AS domaine, q.ql_annee AS annee, q.ql_quartile AS quartile " +
    "FROM quartile q " +
    "JOIN revue r ON q.ql_id_revue = r.re_id_revue " +
    "WHERE r.re_id_revue = :id " +
    "ORDER BY q.ql_annee",
    nativeQuery = true)
    List<Map<String, Object>> findQuartileByRevueId(@Param("id") Long id);


    @Query(value = """
    WITH ranked_quartiles AS (
        SELECT 
            q.ql_id_revue,
            q.ql_annee,
            q.ql_domaine,
            q.ql_quartile,
            r.re_titre,
            r.re_publisher,
            ROW_NUMBER() OVER (
                PARTITION BY q.ql_id_revue, q.ql_annee
                ORDER BY 
                    CASE q.ql_quartile 
                        WHEN 'Q1' THEN 1 
                        WHEN 'Q2' THEN 2 
                        WHEN 'Q3' THEN 3 
                        WHEN 'Q4' THEN 4 
                        ELSE 5 
                    END
            ) AS rk
        FROM quartile q
        JOIN revue r ON q.ql_id_revue = r.re_id_revue
        WHERE r.re_id_revue = :id
    )
    SELECT 
        re_titre AS titre,
        re_publisher AS publisher,
        ql_domaine AS domaine,
        ql_annee AS annee,
        ql_quartile AS quartile
    FROM ranked_quartiles
    WHERE rk = 1
    ORDER BY annee
    """,
    nativeQuery = true)
    List<Map<String, Object>> findBestQuartileByRevueId(@Param("id") Long id);



}

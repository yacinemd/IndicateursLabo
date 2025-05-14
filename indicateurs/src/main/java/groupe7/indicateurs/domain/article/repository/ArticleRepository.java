package groupe7.indicateurs.domain.article.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.article.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    @Query("SELECT a FROM Article a WHERE a.titre LIKE %:titre%")
    List<Article> findByTitre(@Param("titre") String titre);

    
   

    @Query("SELECT a FROM Article a WHERE a.date BETWEEN :date1 AND :date2")
    List<Article> findByYear(@Param("date1") Year date1, @Param("date2") Year date2);


    @Query("SELECT a.date, COUNT(a) FROM Article a GROUP BY a.date ORDER BY a.date DESC")
    List<Object[]> countArticlesByYear();

    

    @Query("SELECT count(a) FROM Article a WHERE a.date BETWEEN :date1 AND :date2 AND a.pays LIKE %:country%")
    Integer countByYearAndCountry(@Param("date1") Year date1, @Param("date2") Year date2, @Param("country") String country);

    @Query("SELECT a FROM Article a WHERE a.date BETWEEN :date1 AND :date2 AND a.pays LIKE %:country%")
    List<Article> findByYearAndCountry(@Param("date1") Year date1, @Param("date2") Year date2, @Param("country") String country);

    @Query(value = """
    SELECT DISTINCT a.*
    FROM personnel pr
    JOIN personne pe ON pr.pr_id_personnel = pe.pe_id_personne
    JOIN liaison_personne_article la ON la.au_id_personne = pe.pe_id_personne
    JOIN article a ON a.at_id_article = la.au_id_article
    WHERE pr.pr_equipe LIKE %:equipe%
    """, nativeQuery = true)
List<Article> findByTeam(@Param("equipe") String equipe);


    

}

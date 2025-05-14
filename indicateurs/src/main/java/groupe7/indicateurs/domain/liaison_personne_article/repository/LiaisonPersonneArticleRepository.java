package groupe7.indicateurs.domain.liaison_personne_article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.liaison_personne_article.model.LiaisonPersonneArticle;
import groupe7.indicateurs.domain.personne.model.Personne;

@Repository
public interface LiaisonPersonneArticleRepository extends JpaRepository<LiaisonPersonneArticle, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    @Query("SELECT l FROM LiaisonPersonneArticle l WHERE l.personne.id = :personneId AND l.article.id = :articleId")
    LiaisonPersonneArticle findByPersonneIdAndArticleId(Long personneId, Long articleId);

    @Query("SELECT COUNT(l) > 0 FROM LiaisonPersonneArticle l WHERE l.personne = :personne AND l.article = :article")
    boolean existsByPersonneAndArticle(Personne personne, Article article);
}

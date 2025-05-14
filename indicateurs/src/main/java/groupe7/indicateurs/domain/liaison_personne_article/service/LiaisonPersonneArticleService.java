package groupe7.indicateurs.domain.liaison_personne_article.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.article.repository.ArticleRepository;
import groupe7.indicateurs.domain.liaison_personne_article.model.LiaisonPersonneArticle;
import groupe7.indicateurs.domain.liaison_personne_article.repository.LiaisonPersonneArticleRepository;
import groupe7.indicateurs.domain.personne.model.Personne;
import groupe7.indicateurs.domain.personne.repository.PersonneRepository;

@Service
public class LiaisonPersonneArticleService {

    private final LiaisonPersonneArticleRepository liaisonPersonneArticleRepository;
    private final PersonneRepository personneRepository;
    private final ArticleRepository articleRepository;

    public LiaisonPersonneArticleService(LiaisonPersonneArticleRepository liaisonPersonneArticleRepository,
                                         PersonneRepository personneRepository, ArticleRepository articleRepository) {
        this.liaisonPersonneArticleRepository = liaisonPersonneArticleRepository;
        this.personneRepository = personneRepository;
        this.articleRepository = articleRepository;
    }

    // Méthode pour lier une personne à un article
    @Transactional
    public void lierPersonneAArticle(Long personneId, Long articleId) {
        Optional<Personne> personneOptional = personneRepository.findById(personneId);
        Optional<Article> articleOptional = articleRepository.findById(articleId);

        if (personneOptional.isPresent() && articleOptional.isPresent()) {
            Personne personne = personneOptional.get();
            Article article = articleOptional.get();

            // Créer la liaison entre la personne et l'article
            LiaisonPersonneArticle liaison = new LiaisonPersonneArticle(personne, article);

            // Sauvegarder la liaison dans la base de données
            liaisonPersonneArticleRepository.save(liaison);
        } else {
            throw new IllegalArgumentException("La personne ou l'article n'existe pas");
        }
    }

    // Méthode pour supprimer une liaison
    @Transactional
    public void supprimerLiaison(Long personneId, Long articleId) {
        LiaisonPersonneArticle liaison = liaisonPersonneArticleRepository
                .findByPersonneIdAndArticleId(personneId, articleId);

        if (liaison != null) {
            // Supprimer la liaison
            liaisonPersonneArticleRepository.delete(liaison);
        } else {
            throw new IllegalArgumentException("Aucune liaison trouvée entre cette personne et cet article");
        }
    }

    // Méthode pour récupérer une liaison par son ID
    public Optional<LiaisonPersonneArticle> findLiaisonById(Long id) {
        return liaisonPersonneArticleRepository.findById(id);
    }

    // Méthode pour vérifier si une personne est liée à un article
    public boolean estLiaisonExiste(Long personneId, Long articleId) {
        return liaisonPersonneArticleRepository
                .findByPersonneIdAndArticleId(personneId, articleId) != null;
    }
}

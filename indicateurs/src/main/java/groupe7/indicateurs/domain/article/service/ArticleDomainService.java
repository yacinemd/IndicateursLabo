package groupe7.indicateurs.domain.article.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.article.repository.ArticleRepository;



@Service
public class ArticleDomainService {
    private final ArticleRepository articleRepository;

    public ArticleDomainService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public void removeArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public List<Article> findArticleByTitre(String titre) {
        return articleRepository.findByTitre(titre);
    }

}

package groupe7.indicateurs.application.article.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.domain.article.model.Article;

@Component
public class ArticleMapper {
    
    public ArticleDTO toDto(Article article) {
        if (article == null) {
            return null;
        }

        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setTitre(article.getTitre());
        dto.setDate(article.getDate());
        dto.setPays(article.getPays());
              
        return dto;
    }

    public Article toEntity(ArticleDTO dto) {
        if (dto == null) {
            return null;
        }

        Article article = new Article();
        article.setId(dto.getId());
        article.setTitre(dto.getTitre());
        article.setDate(dto.getDate());
        article.setPays(dto.getPays());


        return article;
    }
}

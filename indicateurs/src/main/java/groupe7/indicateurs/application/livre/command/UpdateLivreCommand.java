package groupe7.indicateurs.application.livre.command;

import java.time.Year;
import java.util.List;
import java.util.Objects;

import groupe7.indicateurs.application.article.dto.ArticleDTO;

public class UpdateLivreCommand {
   
    private Long id;
    private Year annee;
    private String titre;
    private List<ArticleDTO> articles;


    public UpdateLivreCommand(Long id, Year annee, String titre, List<ArticleDTO> articles) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
        this.annee = Objects.requireNonNull(annee, "L'année est obligatoire");
        this.titre = Objects.requireNonNull(titre, "Le titre est obligatoire");
        this.articles=articles;
    }

    public long getId() {
        return id;
    }
    
    public Year getAnnee() {
        return annee;
    }

    public String getTitre() {
        return titre;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

}

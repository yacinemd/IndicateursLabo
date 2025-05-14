package groupe7.indicateurs.application.revue.command;

import java.time.Year;
import java.util.List;
import java.util.Objects;

import groupe7.indicateurs.application.article.dto.ArticleDTO;


public class CreateRevueCommand extends RevueCommand {
    private String titre;
    private String domaine;
    private Year annee;
    private List<ArticleDTO> articles;
    private String publisher;
    private int index;


    public CreateRevueCommand(String titre, String domaine, Year annee, List<ArticleDTO> articles,String publisher,int index) {
        this.titre = Objects.requireNonNull(titre, "Le titre est obligatoire");
        this.domaine = Objects.requireNonNull(domaine, "Le domaine est obligatoire");
        this.annee = Objects.requireNonNull(annee, "L'année est obligatoire");
        this.articles=articles;
        this.publisher=publisher;
        this.index=index;
    }

    // Getters
    
    public String getTitre() {
        return titre;
    }

    public String getDomaine() {
        return domaine;
    }

    public Year getAnnee() {
        return annee;
    }
    
    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public int getIndex(){
        return this.index;
    }
    
    public String getPublisher(){
        return this.publisher;
    }
}

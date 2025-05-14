package groupe7.indicateurs.application.personne.command;

import java.util.List;
import java.util.Objects;

import groupe7.indicateurs.application.article.dto.ArticleDTO;

public class CreatePersonneCommand extends PersonneCommand {
    private String nom;
    private String prenom;
    private Boolean hdr;
    private List<ArticleDTO> articles;

    public CreatePersonneCommand(String nom, String prenom, Boolean hdr, List<ArticleDTO> articles) {
        this.nom = Objects.requireNonNull(nom, "Le nom est obligatoire");
        this.prenom = Objects.requireNonNull(prenom, "Le prénom est obligatoire");
        this.hdr = Objects.requireNonNull(hdr, "Le statut HDR est obligatoire");
        this.articles=articles;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Boolean getHdr() {
        return hdr;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

}

package groupe7.indicateurs.application.personne.command;

import java.util.List;
import java.util.Objects;

import groupe7.indicateurs.application.article.dto.ArticleDTO;

public class UpdatePersonneCommand extends PersonneCommand {
    private long id;
    private String nom;
    private String prenom;
    private Boolean hdr;
    private List<ArticleDTO> articles;

    public UpdatePersonneCommand(String nom, String prenom, Boolean hdr,List<ArticleDTO> articles) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
        this.nom = Objects.requireNonNull(nom, "Le nom est obligatoire");
        this.prenom = Objects.requireNonNull(prenom, "Le prénom est obligatoire");
        this.hdr = Objects.requireNonNull(hdr, "Le statut HDR est obligatoire");
        this.articles=articles;
    }
    
    public long getId() {
        return id;
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

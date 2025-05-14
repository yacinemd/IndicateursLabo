package groupe7.indicateurs.application.autre_poste.command;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.domain.projet.model.Projet;

public class UpdateAutrePosteCommand extends AutrePosteCommand {
    private Long id;
    private String nom;
    private String prenom;
    private Boolean hdr;
    private String statut;
    private String equipe;
    private Boolean titulaire;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private List<Projet> projets;
    private String titre;
    private List<ArticleDTO> articles;

    public UpdateAutrePosteCommand(Long id, String nom, String prenom, Boolean hdr, String statut, String equipe, Boolean titulaire, LocalDate date_debut, LocalDate date_fin, List<Projet> projets, String titre, List<ArticleDTO> articles) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
        this.nom = Objects.requireNonNull(nom, "Le nom est obligatoire");
        this.prenom = Objects.requireNonNull(prenom, "Le prénom est obligatoire");
        this.hdr = Objects.requireNonNull(hdr, "Le statut HDR est obligatoire");
        this.statut = Objects.requireNonNull(statut, "Le statut est obligatoire");
        this.equipe = Objects.requireNonNull(equipe, "L'équipe est obligatoire");
        this.titulaire = Objects.requireNonNull(titulaire, "Le statut de titulaire est obligatoire");
        this.date_debut = Objects.requireNonNull(date_debut, "La date de début est obligatoire");
        this.date_fin = date_fin;
        this.projets = projets;
        this.titre = Objects.requireNonNull(titre, "Le titre est obligatoire");
        this.articles=articles;
    }

    public Long getId() {
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

    public String getStatut() {
        return statut;
    }

    public String getEquipe() {
        return equipe;
    }

    public Boolean getTitulaire() {
        return titulaire;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public List<Projet> getProjets() {
        return projets;
    }
    public String getTitre (){
        return titre;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }
}

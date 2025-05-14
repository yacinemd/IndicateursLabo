package groupe7.indicateurs.application.autre_poste.dto;

import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.application.projet.dto.ProjetDTO;

public class AutrePosteDTO {

    private Long id;
    private String nom;
    private String prenom;
    private Boolean hdr;
    private String statut;
    private String equipe;
    private Boolean titulaire;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private List<ProjetDTO> projets;
    private String titre;
    private List<ArticleDTO> articles;

    public AutrePosteDTO() {
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }

    public String getTitre() {
        return titre;
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

    public List<ProjetDTO> getProjets() {
        return projets;
    }

    // Setters
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public void setTitulaire(Boolean titulaire) {
        this.titulaire = titulaire;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public void setProjets(List<ProjetDTO> projets) {
        this.projets = projets;
    }

    public void setArticles(List<ArticleDTO> articles){
        this.articles=articles;
    }

    public List<ArticleDTO> getArticles(){
        return articles;
    }
}

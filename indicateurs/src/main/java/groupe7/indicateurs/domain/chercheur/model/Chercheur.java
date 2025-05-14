package groupe7.indicateurs.domain.chercheur.model;

import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.personnel.model.Personnel;
import groupe7.indicateurs.domain.projet.model.Projet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Chercheur {

    @Id
    @Column(name = "ch_id_chercheur")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ch_id_chercheur", referencedColumnName = "pr_id_personnel")
    @MapsId
    private Personnel personnel;

    @ManyToMany
    @JoinTable(
        name = "liaison_personnel_projet",
        joinColumns = @JoinColumn(name = "lc_id_personnel"),
        inverseJoinColumns = @JoinColumn(name = "lc_id_projet")
    )
    private List<Projet> projets;

     @ManyToMany
    @JoinTable(
        name = "liaison_personne_article",
        joinColumns = @JoinColumn(name = "au_id_personne"),
        inverseJoinColumns = @JoinColumn(name = "au_id_article")
    )
    private List<Article> articles;

    public Chercheur() {
    }

    public Chercheur(Personnel personnel) {
        this.personnel = personnel;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public Personnel getPersonnel() {
        return personnel;
    }
    
    public String getNom() {
        return personnel != null ? personnel.getNom() : null;
    }

    public String getPrenom() {
        return personnel != null ? personnel.getPrenom() : null;
    }

    public Boolean getHdr() {
        return personnel != null ? personnel.getHdr() : null;
    }

    public String getStatut() {
        return personnel != null ? personnel.getStatut() : null;
    }

    public String getEquipe() {
        return personnel != null ? personnel.getEquipe() : null;
    }

    public Boolean getTitulaire() {
        return personnel != null ? personnel.getTitulaire() : null;
    }

    public LocalDate getDate_debut() {
        return personnel != null ? personnel.getDate_debut() : null;
    }

    public LocalDate getDate_fin() {
        return personnel != null ? personnel.getDate_fin() : null;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public void setNom(String nom) {
        this.personnel.setNom(nom);
    }

    public void setPrenom(String prenom) {
        this.personnel.setPrenom(prenom);
    }

    public void setHdr(Boolean hdr) {
        this.personnel.setHdr(hdr);
    }

    public void setStatut(String statut) {
        this.personnel.setStatut(statut);
    }

    public void setEquipe(String equipe) {
        this.personnel.setEquipe(equipe);
    }

    public void setTitulaire(Boolean titulaire) {
        this.personnel.setTitulaire(titulaire);
    }

    public void setDate_debut(LocalDate date_debut) {
        this.personnel.setDate_debut(date_debut);
    }

    public void setDate_fin(LocalDate date_fin) {
        this.personnel.setDate_fin(date_fin);
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

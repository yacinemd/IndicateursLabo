package groupe7.indicateurs.domain.personnel.model;

import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.personne.model.Personne;
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
public class Personnel {

    @Id
    @Column(name = "pr_id_personnel")
    private Long id;

    @OneToOne
    @JoinColumn(name = "pr_id_personnel", referencedColumnName = "pe_id_personne")
    @MapsId
    private Personne personne;

    @Column(name = "pr_statut")
    private String statut;

    @Column(name = "pr_equipe")
    private String equipe;

    @Column(name = "pr_titulaire")
    private Boolean titulaire;

    @Column(name = "pr_date_debut")
    private LocalDate date_debut;

    @Column(name = "pr_date_fin")
    private LocalDate date_fin;

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

    public Personnel() {
    }

    /*public Personnel(String nom,String prenom , Boolean hdr, String statut, String equipe, Boolean titulaire, LocalDate date_debut, LocalDate date_fin) {
        this.personne.setNom(nom);
        this.personne.setPrenom(prenom);
        this.personne.setHdr(hdr);
        this.statut = statut;
        this.equipe = equipe;
        this.titulaire = titulaire;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }*/
    public Personnel(String statut, String equipe, Boolean titulaire, LocalDate date_debut, LocalDate date_fin) {
        this.statut = statut;
        this.equipe = equipe;
        this.titulaire = titulaire;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    public Personnel(Personne personne) {
        this.personne = personne;
    }

    public Long getId() {
        return id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public String getNom() {
        return personne != null ? personne.getNom() : null;
    }

    public String getPrenom() {
        return personne != null ? personne.getPrenom() : null;
    }

    public Boolean getHdr() {
        return personne != null ? personne.getHdr() : null;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public void setNom(String nom) {
        this.personne.setNom(nom);
    }

    public void setPrenom(String prenom) {
        this.personne.setPrenom(prenom);
    }

    public void setHdr(Boolean hdr) {
        this.personne.setHdr(hdr);
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

    public List<Projet> getProjets() {
        return projets;
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
package groupe7.indicateurs.domain.doctorant.model;

import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.personnel.model.Personnel;
import groupe7.indicateurs.domain.projet.model.Projet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Doctorant {

    @Id
    @Column(name = "do_id_doctorant")
    private Long id;

    @Column(name = "do_date_de_soutenance")
    private LocalDate date_soutenance;

    @Column(name = "do_encadrant")
    private String encadrant;

    @OneToOne
    @JoinColumn(name = "do_id_doctorant", referencedColumnName = "pr_id_personnel")
    @MapsId
    private Personnel personnel;

    public Doctorant() {

    }

    public Doctorant(LocalDate date_soutenance, String encadrant, Personnel personnel) {
        this.date_soutenance = date_soutenance;
        this.encadrant = encadrant;
        this.personnel = personnel;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getEncadrant() {
        return encadrant;
    }

    public Personnel getPersonnel() {
        return personnel;
    }
    
    public LocalDate getDate_soutenance(){
        return date_soutenance;
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
        return personnel != null ? personnel.getProjets() : null;
    }

    public List<Article> getArticles() {
        return personnel != null ? personnel.getArticles() : null;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setEncadrant(String encadrant) {
        this.encadrant = encadrant;
    }

    public void setDate_soutenance(LocalDate date_soutenance) {
        this.date_soutenance = date_soutenance;
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
        this.personnel.setProjets(projets);
    }

    public void setArticles(List<Article> articles) {
        this.personnel.setArticles(articles);
    }


}

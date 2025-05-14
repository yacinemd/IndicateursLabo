package groupe7.indicateurs.web.chercheur.dto;

import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.web.projet.dto.ProjetWebDTO;

public class ChercheurWebDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Boolean hdr;
    private String statut;
    private String equipe;
    private Boolean titulaire;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private List<ProjetWebDTO> projets;

    public ChercheurWebDTO() {
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

    public List<ProjetWebDTO> getProjets() {
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

    public void setProjets(List<ProjetWebDTO> projets) {
        this.projets = projets;
    }
}

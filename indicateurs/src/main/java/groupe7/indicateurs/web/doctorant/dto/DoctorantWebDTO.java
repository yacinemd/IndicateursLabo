package groupe7.indicateurs.web.doctorant.dto;

import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.web.projet.dto.ProjetWebDTO;

public class DoctorantWebDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String statut;
    private String equipe;
    private boolean titulaire;
    private String encadrant;
    private LocalDate date_soutenance;
    private List<ProjetWebDTO> projets;


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

    public String getStatut() {
        return statut;
    }

    public String getEquipe() {
        return equipe;
    }

    public Boolean getTitulaire() {
        return titulaire;
    }

    public String getEncadrant() {
        return encadrant;
    }

    public LocalDate getDate_soutenance() {
        return date_soutenance;
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

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public void setTitulaire(Boolean titulaire) {
        this.titulaire = titulaire;
    }

    public void setIdEncadrant(String encadrant) {
        this.encadrant = encadrant;
    }

    public void setDate_soutenance(LocalDate date_soutenance) {
        this.date_soutenance = date_soutenance;
    }

    public void setProjets(List<ProjetWebDTO> projets) {
        this.projets = projets;
    }
}

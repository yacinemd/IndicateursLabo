package groupe7.indicateurs.web.personnel.dto;

import java.util.List;

import groupe7.indicateurs.web.projet.dto.ProjetWebDTO;

public class PersonnelWebDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String statut;
    private String equipe;
    private boolean titulaire;
    private List<ProjetWebDTO> projets;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public boolean getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(boolean titulaire) {
        this.titulaire = titulaire;
    }

    public List<ProjetWebDTO> getProjets() {
        return projets;
    }

    public void setProjets(List<ProjetWebDTO> projets) {
        this.projets = projets;
    }
}

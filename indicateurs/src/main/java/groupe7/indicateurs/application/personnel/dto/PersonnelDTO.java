package groupe7.indicateurs.application.personnel.dto;

import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.application.projet.dto.ProjetDTO;
import groupe7.indicateurs.domain.personnel.model.Personnel;

public class PersonnelDTO {

    private Long id;
    private String nom;
    private String prenom;
    private Boolean hdr;
    private String statut;
    private String equipe;
    private boolean titulaire;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<ProjetDTO> projets;
    private List<ArticleDTO> articles;

    public PersonnelDTO() {
    }

    public PersonnelDTO(Long id, String nom, String prenom, Boolean hdr, String statut, String equipe, boolean titulaire,
                        LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.hdr = hdr;
        this.statut = statut;
        this.equipe = equipe;
        this.titulaire = titulaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

     public static PersonnelDTO toDto(Personnel entity) {
        return new PersonnelDTO(
            entity.getId(),
            entity.getNom(),
            entity.getPrenom(),
            entity.getHdr(),
            entity.getStatut(),
            entity.getEquipe(),
            entity.getTitulaire(),
            entity.getDate_debut(),
            entity.getDate_fin()
            
        );
    }

    public static Personnel toEntity(PersonnelDTO dto) {
        return new Personnel(
            dto.getStatut(),
            dto.getEquipe(),
            dto.getTitulaire(),
            dto.getDateDebut(),
            dto.getDateFin()
        );
    }

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

    public Boolean getHdr() {
        return hdr;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<ProjetDTO> getProjets() {
        return projets;
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

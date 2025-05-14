package groupe7.indicateurs.application.projet.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.application.personnel.dto.PersonnelDTO;
import groupe7.indicateurs.domain.projet.model.Projet;

public class ProjetDTO {

    private Long id;
    private String nom;
    private Long idContributeurProjet;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String type;
    private BigDecimal budget;
    private Boolean partenariat;
    private List<PersonnelDTO> personnels;

    public ProjetDTO() {
    }

    public ProjetDTO(Long id, String nom, Long idContributeurProjet, LocalDate dateDebut, LocalDate dateFin, String type, BigDecimal budget, Boolean partenariat) {
        this.id = id;
        this.nom = nom;
        this.idContributeurProjet = idContributeurProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.budget = budget;
        this.partenariat = partenariat;
    }

    public static ProjetDTO toDto(Projet entity) {
        return new ProjetDTO(
            entity.getId(),
            entity.getNom(),
            entity.getIdContributeurProjet(),
            entity.getDateDebut(),
            entity.getDateFin(),
            entity.getType(),
            entity.getBudget(),
            entity.getPartenariat()
        );
    }

    public static Projet toEntity(ProjetDTO dto) {
        return new Projet(
            dto.getNom(),
            dto.getIdContributeurProjet(),
            dto.getDateDebut(),
            dto.getDateFin(),
            dto.getType(),
            dto.getBudget(),
            dto.getPartenariat()
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

    public Long getIdContributeurProjet() {
        return idContributeurProjet;
    }

    public void setIdContributeurProjet(Long idContributeurProjet) {
        this.idContributeurProjet = idContributeurProjet;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Boolean getPartenariat() {
        return partenariat;
    }

    public void setPartenariat(Boolean partenariat) {
        this.partenariat = partenariat;
    }
    public List<PersonnelDTO> getPersonnels() {
        return personnels;
    }
    public void setPersonnels(List<PersonnelDTO> personnels) {
        this.personnels = personnels;
    }


}

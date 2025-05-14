package groupe7.indicateurs.domain.projet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import groupe7.indicateurs.domain.personnel.model.Personnel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pj_id_projet")
    private Long id;

    @Column(name = "pj_nom")
    private String nom;

    @Column(name = "pj_id_contributeurprojet")
    private Long idContributeurProjet;

    @Column(name = "pj_date_debut")
    private LocalDate dateDebut;

    @Column(name = "pj_date_fin")
    private LocalDate dateFin;

    @Column(name = "pj_type")
    private String type;

    @Column(name = "pj_budget")
    private BigDecimal budget;

    @Column(name = "pj_partenariat")
    private Boolean partenariat;

    
    @ManyToMany
    @JoinTable(
        name = "liaison_personnel_projet",
        joinColumns = @JoinColumn(name = "lc_id_projet"),
        inverseJoinColumns = @JoinColumn(name = "lc_id_personnel")
    )
    private List<Personnel> personnels;

    public Projet() {
    }

    public Projet(String nom, Long idContributeurProjet, LocalDate dateDebut, LocalDate dateFin, String type, BigDecimal budget, Boolean partenariat) {
        this.nom = nom;
        this.idContributeurProjet = idContributeurProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.budget = budget;
        this.partenariat = partenariat;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Long getIdContributeurProjet() {
        return idContributeurProjet;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public Boolean getPartenariat() {
        return partenariat;
    }

    public List<Personnel> getPersonnels() {
        return personnels;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIdContributeurProjet(Long idContributeurProjet) {
        this.idContributeurProjet = idContributeurProjet;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setPartenariat(Boolean partenariat) {
        this.partenariat = partenariat;
    }

    public void setPersonnels(List<Personnel> personnels) {
        this.personnels = personnels;
    }
    
}

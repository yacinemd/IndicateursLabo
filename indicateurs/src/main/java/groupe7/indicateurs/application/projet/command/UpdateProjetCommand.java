package groupe7.indicateurs.application.projet.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import groupe7.indicateurs.application.personnel.dto.PersonnelDTO;

public class UpdateProjetCommand extends ProjetCommand {
    private long id;
    private String nom;
    private Long idContributeurProjet;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String type;
    private BigDecimal budget;
    private Boolean partenariat;
    private List<PersonnelDTO> personnels;

    public UpdateProjetCommand(long id, String nom, Long idContributeurProjet, LocalDate dateDebut, LocalDate dateFin, String type, BigDecimal budget, Boolean partenariat,List<PersonnelDTO> personnels) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
        this.nom = Objects.requireNonNull(nom, "Le nom est obligatoire");
        this.idContributeurProjet = Objects.requireNonNull(idContributeurProjet, "L'ID du contributeur projet est obligatoire");
        this.dateDebut = Objects.requireNonNull(dateDebut, "La date de début est obligatoire");
        this.dateFin = dateFin; // Peut être null
        this.type = Objects.requireNonNull(type, "Le type est obligatoire");
        this.budget = Objects.requireNonNull(budget, "Le budget est obligatoire");
        this.partenariat = Objects.requireNonNull(partenariat, "Le partenariat est obligatoire");
        this.personnels = personnels;
    }

    public long getId() {
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

    public List<PersonnelDTO> getPersonnels(){
        return personnels;
    }
}

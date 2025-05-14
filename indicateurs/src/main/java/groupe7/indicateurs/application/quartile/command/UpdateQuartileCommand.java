package groupe7.indicateurs.application.quartile.command;

import java.time.Year;
import java.util.Objects;

import groupe7.indicateurs.application.revue.dto.RevueDTO;

public class UpdateQuartileCommand {
    private Long id;
    private Year annee;
    private String quartile;
    private String domaine;
    private RevueDTO revue;

    public UpdateQuartileCommand(Long id, Year annee, String quartile, String domaine, RevueDTO revue) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
        this.annee = Objects.requireNonNull(annee, "L'année est obligatoire");
        this.quartile = Objects.requireNonNull(quartile, "Le quartile est obligatoire");
        this.domaine = Objects.requireNonNull(domaine, "Le domaine est obligatoire");
        this.revue = revue;
    }

    public Long getId() {
        return id;
    }

    public Year getAnnee() {
        return annee;
    }

    public String getQuartile() {
        return quartile;
    }

    public String getDomaine() {
        return domaine;
    }

    public RevueDTO getRevue() {
        return revue;
    }
}

package groupe7.indicateurs.web.quartile.dto;

import java.time.Year;

import groupe7.indicateurs.web.revue.dto.RevueWebDTO;

public class QuartileWebDTO {
    private Long id;
    private Year annee;
    private String quartile;
    private String domaine;
    private RevueWebDTO revue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getAnnee() {
        return annee;
    }

    public void setAnnee(Year annee) {
        this.annee = annee;
    }

    public String getQuartile() {
        return quartile;
    }

    public void setQuartile(String quartile) {
        this.quartile = quartile;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public RevueWebDTO getRevue() {
        return revue;
    }

    public void setRevue(RevueWebDTO revue) {
        this.revue = revue;
    }
}

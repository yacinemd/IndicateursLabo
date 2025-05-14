package groupe7.indicateurs.web.conference.dto;

import java.time.Year;

public class ConferenceWebDTO {
    private Long id;
    private String titre;
    private Year annee;
    private String pays;


    // Getters

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }


    public Year getAnnee() {
        return annee;
    }

    public String getPays() {
        return pays;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public void setAnnee(Year annee) {
        this.annee = annee;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }

}

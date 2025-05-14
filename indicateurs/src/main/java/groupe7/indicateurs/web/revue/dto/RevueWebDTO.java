package groupe7.indicateurs.web.revue.dto;

import java.time.Year;

public class RevueWebDTO {
    private Long id;
    private String titre;
    private Year annee;


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

}

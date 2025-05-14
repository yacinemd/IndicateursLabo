package groupe7.indicateurs.web.livre.dto;

import java.time.Year;

public class LivreWebDTO {
    private Long id;
    private Year annee;
    private String titre;

    // Getters

    public Long getId() {
        return id;
    }
   
    public Year getAnnee() {
        return annee;
    }

    public String getTitre() {
        return titre;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnnee(Year annee) {
        this.annee = annee;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}

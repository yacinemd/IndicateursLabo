package groupe7.indicateurs.application.livre.dto;

import java.time.Year;

import groupe7.indicateurs.domain.livre.model.Livre;

public class LivreDTO {
    private Long id;
    private Year annee;
    private String titre;
    private String pays;

    public LivreDTO(){

    }

    public LivreDTO(Long id, Year annee, String titre, String pays){
        this.pays = pays;
        this.id = id;
        this.annee=annee;
        this.titre=titre;
    }

    public static LivreDTO toDto(Livre entity) {
        return new LivreDTO(
            entity.getId(),
            entity.getAnnee(),
            entity.getTitre(),
            entity.getPays()
        );
    }

    public static Livre toEntity(LivreDTO dto) {
        return new Livre(
            dto.getAnnee(),
            dto.getTitre(),
            dto.getPays()
        );
    }


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

    public String getPays() {
        return pays;
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

    public void setPays(String pays) {
        this.pays = pays;
    }
}

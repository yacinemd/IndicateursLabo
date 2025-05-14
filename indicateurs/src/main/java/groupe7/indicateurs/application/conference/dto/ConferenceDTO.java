package groupe7.indicateurs.application.conference.dto;

import java.time.Year;

import groupe7.indicateurs.domain.conference.model.Conference;

public class ConferenceDTO {

    private Long id;
    private String titre;
    private Year annee;
    private String pays;

    public ConferenceDTO() {
    }

    public ConferenceDTO(Long id, String titre, Year annee, String pays) {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.pays = pays;
    }

    public static ConferenceDTO toDto(Conference entity) {
        return new ConferenceDTO(
            entity.getId(),
            entity.getTitre(),
            entity.getAnnee(),
            entity.getPays()
        );
    }

    public static Conference toEntity(ConferenceDTO dto) {
        return new Conference(
            dto.getTitre(),
            dto.getAnnee(),
            dto.getPays()
        );
    }

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

    public String getPays(){
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

    public void setPays(String pays){
        this.pays=pays;
    }
}
